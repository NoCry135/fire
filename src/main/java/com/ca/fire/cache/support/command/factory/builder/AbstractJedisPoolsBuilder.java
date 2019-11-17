package com.ca.fire.cache.support.command.factory.builder;

import com.ca.fire.cache.support.command.factory.JedisConfig;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public abstract class AbstractJedisPoolsBuilder {
    private static final String SERVER_SEPARATOR = ",";
    private static final long MIN_EVICTABLE_IDLE_TIME_MILLIS = 180000;
    private static final long TIME_BETWEEN_EVICTION_RUNS_MILLIS = 60000;

    public boolean support(int mode) {
        return getSupportedMode().getValue() == mode;
    }

    /**
     * 获取builder对应的模式
     *
     * @return jedisPool模式
     */
    protected abstract JedisPoolMode getSupportedMode();


    static Set<String> getServerSet(String servers) {
        Preconditions.checkArgument(StringUtils.isNotBlank(servers), "servers is blank!");
        String[] serverArr = servers.split(SERVER_SEPARATOR);
        return new HashSet<String>(Arrays.asList(serverArr));
    }

    static GenericObjectPoolConfig getPoolConfig(JedisConfig jedisConfig) {
        return jedisConfig.getPoolConfig() == null ? defaultPoolConfig() : jedisConfig.getPoolConfig();
    }

    static GenericObjectPoolConfig defaultPoolConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(poolConfig.getMaxTotal());
        poolConfig.setTimeBetweenEvictionRunsMillis(TIME_BETWEEN_EVICTION_RUNS_MILLIS);
        poolConfig.setMinEvictableIdleTimeMillis(MIN_EVICTABLE_IDLE_TIME_MILLIS);
        poolConfig.setSoftMinEvictableIdleTimeMillis(MIN_EVICTABLE_IDLE_TIME_MILLIS);
        return poolConfig;
    }

}
