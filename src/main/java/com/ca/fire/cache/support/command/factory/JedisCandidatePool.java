package com.ca.fire.cache.support.command.factory;

import com.ca.fire.cache.support.command.factory.builder.JedisPoolBuilder;
import com.ca.fire.cache.support.command.factory.builder.JedisPoolBuilderFactory;
import com.ca.fire.cache.support.command.factory.builder.JedisPoolWrapper;
import com.ca.fire.cache.support.command.factory.builder.StaticJedisPoolBuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 */
public class JedisCandidatePool extends JedisPool implements PrimitiveCommandExceptionHandler, InitializingBean {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private static final int DEFAULT_WAIT_TIMEOUT_MILLS = 3000;
    private static final int MIN_AUTO_CHECK_INTERVAL_MILLS = 1000;
    private static final int DEFAULT_AUTO_CHECK_INTERVAL_MILLS = 30000;
    private static final String SUCCEED_REPLY = "OK";
    private static final String KEY_FOR_CHECK = "com.ca.fire.lusir.cache.check";
    private static final String VALUE_FOR_CHECK = "0";
    private AtomicBoolean electing = new AtomicBoolean(false);
    private boolean ignoreCandidatePoolsNotFound = false;
    private boolean enableIntervalCheck = true;
    private Checker checker;
    private List<JedisPoolWrapper> candidatePools;
    private JedisPoolWrapper currentCandidate;
    private JedisPoolBuilderFactory jedisPoolBuilderFactory;
    private JedisConfig jedisConfig;
    private int poolMode;
    private long autoCheckIntervalMills;

    public JedisCandidatePool(JedisConfig jedisConfig, int poolMode) {
        this.jedisConfig = jedisConfig;
        this.poolMode = poolMode;
        this.candidatePools = new ArrayList<JedisPoolWrapper>();
    }

    @Override
    public Jedis getResource() {
        if (candidatePools.isEmpty()) {
            return super.getResource();
        }
        if (isElecting()) {
            waitForElection();
        }
        return currentCandidate.getResource();
    }

    private void waitForElection() {
        synchronized (this) {
            if (isElecting()) {
                try {
                    logger.info("Wait for new candidate...");
                    wait(DEFAULT_WAIT_TIMEOUT_MILLS);
                } catch (InterruptedException e) {
                    logger.error("Interrupted while wait for elect...", e);
                }
            }
        }
    }

    private void elect() {
        if (candidatePools.isEmpty()) {
            return;
        }
        if (currentCandidate == null) {
            if (justOneCandidate() && isValid(candidatePools.get(0))) {
                currentCandidate = candidatePools.get(0);
            } else {
                doElect();
            }
            if (currentCandidate == null && !ignoreCandidatePoolsNotFound) {
                throw new RuntimeException("Can not decide candidate jedis pool!");
            }
            return;
        }
        if (justOneCandidate()) {
            return;
        }
        if (isElecting()) {
            logger.debug("It is electing...");
            return;
        }
        electing.set(true);
        synchronized (this) {
            if (isElecting()) {
                doElect();
                electing.set(false);
                notifyAll();
            }
        }
    }

    private boolean isElecting() {
        return electing.get();
    }

    private void doElect() {
        JedisPoolWrapper temp = currentCandidate;
        logger.info("Start to elect...old candidate -> {}", temp == null ? null : temp.getDescription());
        for (JedisPoolWrapper pool : candidatePools) {
            if (isValid(pool)) {
                currentCandidate = pool;
                logger.info("Pool elected!description -> {}, candidate was changed:{}", pool.getDescription(), temp == null || !temp.equals(currentCandidate));
                return;
            } else {
                logger.warn("It is invalid pool!description -> {}", pool.getDescription());
            }
        }
        logger.warn("Election was done,but no jedisPool is valid!");
    }

    private boolean isValid(JedisPoolWrapper pool) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return isMater(jedis);
        } catch (Exception ex) {
            logger.error("Exception while check the pool is valid or not", ex);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    private boolean isMater(Jedis jedis) {
        return SUCCEED_REPLY.equals(jedis.set(KEY_FOR_CHECK, VALUE_FOR_CHECK));
    }

    @Override
    public void destroy() {
        if (checker != null) {
            checker.shutdown();
        }
        if (!candidatePools.isEmpty()) {
            for (JedisPoolWrapper pool : candidatePools) {
                pool.destroy();
            }
        }
        super.destroy();
    }

    @Override
    public void handle(Pool<Jedis> currentPool, Exception ex) {
        if (ignoreToSelect()) {
            return;
        }
        elect();
    }

    private boolean ignoreToSelect() {
        return candidatePools.isEmpty() || justOneCandidate();
    }

    private boolean justOneCandidate() {
        return candidatePools.size() == 1;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jedisPoolBuilderFactory == null) {
            jedisPoolBuilderFactory = new StaticJedisPoolBuilderFactory();
        }
        if (autoCheckIntervalMills < MIN_AUTO_CHECK_INTERVAL_MILLS) {
            autoCheckIntervalMills = DEFAULT_AUTO_CHECK_INTERVAL_MILLS;
        }
        decideCandidatePools();
        elect();
        if (needIntervalCheck()) {
            startChecker();
        }
    }

    private void decideCandidatePools() {
        List<JedisPoolBuilder> poolBuilders = jedisPoolBuilderFactory.create();
        for (JedisPoolBuilder poolBuilder : poolBuilders) {
            if (poolBuilder.support(poolMode)) {
                candidatePools.addAll(poolBuilder.build(jedisConfig));
                break;
            }
        }
        if (candidatePools.isEmpty() && !ignoreCandidatePoolsNotFound) {
            throw new IllegalStateException("No any candidate pool!Maybe no builder support the mode:" + poolMode);
        }
    }

    private boolean needIntervalCheck() {
        return candidatePools.size() > 1 && enableIntervalCheck;
    }

    private void startChecker() {
        checker = new Checker();
        checker.setName("JedisCandidatePoolChecker");
        checker.setDaemon(true);
        checker.start();
    }

    public void setIgnoreCandidatePoolsNotFound(boolean ignoreCandidatePoolsNotFound) {
        this.ignoreCandidatePoolsNotFound = ignoreCandidatePoolsNotFound;
    }

    public void setEnableIntervalCheck(boolean enableIntervalCheck) {
        this.enableIntervalCheck = enableIntervalCheck;
    }

    public void setAutoCheckIntervalMills(long autoCheckIntervalMills) {
        this.autoCheckIntervalMills = autoCheckIntervalMills;
    }

    class Checker extends Thread {
        private AtomicBoolean running = new AtomicBoolean(false);

        @Override
        public void run() {
            running.set(true);
            logger.debug("Start to check candidate pool...");
            while (running.get()) {
                try {
                    if (currentCandidate != null) {
                        if (!isValid(currentCandidate)) {
                            elect();
                        }
                    }
                } catch (Exception ex) {
                    logger.error("Exception while check current candidate pool!", ex);
                }
                sleepQuietly(autoCheckIntervalMills);
            }
        }

        void shutdown() {
            logger.debug("Stop to check candidate pool...");
            running.set(false);
            logger.debug("Checker is stopped!");
        }

        private void sleepQuietly(long mills) {
            try {
                Thread.sleep(mills);
            } catch (InterruptedException e) {
                logger.error("Sleep interrupted!", e);
            }
        }

    }

}
