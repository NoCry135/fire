package com.ca.fire.until.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 基于redis的分布式锁实现
 */
@Slf4j
public class RedisDistributedLockUtils {

    /**
     * 锁超时时间
     */
    private final static int DEFAULT_EXPIRE_TIME = 15 * 1000;

    /**
     * 重试时间间隔
     */
    private final static int RETRY_TIME_INTERVAL = 3000;

    /**
     * 最大重试此时
     */
    private final static int MAX_RETRY_TIMES = 5;

    /**
     * 默认重试次数
     */
    private final static int DEFAULT_RETRY_TIMES = 3;


    private static String get(RedisTemplate redisTemplate, final String key) {
        Object obj = redisTemplate.opsForValue().get(key);
        return obj != null ? obj.toString() : null;
    }

    private static boolean setNX(RedisTemplate redisTemplate, String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    private static String getAndSet(RedisTemplate redisTemplate, final String key, final String value) {
        Object obj = redisTemplate.opsForValue().getAndSet(key, value);
        return obj != null ? obj.toString() : null;
    }

    /**
     * 实现思路:<br>
     * 1.尝试使用setNX获取锁;<br>
     * 2.如果setNX失败，则检测锁的过期时间；<br>
     * 3.如果锁已经过期，则getSet重置锁的过期时间;<br>
     * 4.只有当getSet前后返回的过期时间一致时，才表示重新获取到了锁，否则获取锁失败
     *
     * @param redisTemplate
     * @param lockKey
     * @return
     * @throws InterruptedException
     */
    public static boolean lock(RedisTemplate redisTemplate, String lockKey) {
        long currentTimestamp = System.currentTimeMillis();
        // 锁的默认过期时间
        String expiresStr = String.valueOf(currentTimestamp + DEFAULT_EXPIRE_TIME + 1);
        if (setNX(redisTemplate, lockKey, expiresStr)) {
            // 成功获取到锁
            return true;
        }
        // 获取锁失败，检查锁的过期时间
        String oldExpireTimeStrBefore = get(redisTemplate, lockKey);
        if (oldExpireTimeStrBefore != null
                && Long.parseLong(oldExpireTimeStrBefore) < currentTimestamp) {
            // 重置锁的到期时间,返回旧的过期时间
            String oldExpireTimeStrAfter = getAndSet(redisTemplate, lockKey, expiresStr);
            // 只有当getSet前后返回的过期时间一致时，才表示重新获取到了锁，否则获取锁失败
            if (oldExpireTimeStrBefore.equals(oldExpireTimeStrAfter)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param redisTemplate
     * @param lockKey
     * @param expireTime    锁的过期时间
     * @return
     */
    public static boolean lock(RedisTemplate redisTemplate, String lockKey, int expireTime) {
        long currentTimestamp = System.currentTimeMillis();
        // 锁的过期时间
        String expiresStr = String.valueOf(currentTimestamp + expireTime + 1);
        if (setNX(redisTemplate, lockKey, expiresStr)) {
            // 成功获取到锁
            return true;
        }
        // 获取锁失败，检查锁的过期时间
        String oldExpireTimeStrBefore = get(redisTemplate, lockKey);
        if (oldExpireTimeStrBefore != null
                && Long.parseLong(oldExpireTimeStrBefore) < currentTimestamp) {
            // 重置锁的到期时间,返回旧的过期时间
            String oldExpireTimeStrAfter = getAndSet(redisTemplate, lockKey, expiresStr);
            // 只有当getSet前后返回的过期时间一致时，才表示重新获取到了锁，否则获取锁失败
            if (oldExpireTimeStrBefore.equals(oldExpireTimeStrAfter)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param redisTemplate
     * @param lockKey
     * @param retryTimes
     * @return
     */
    public static boolean lock(RedisTemplate redisTemplate, String lockKey, int retryTimes, int expireTime) {
        if (retryTimes < 0) {
            retryTimes = 1;
        } else if (retryTimes > MAX_RETRY_TIMES) {
            retryTimes = MAX_RETRY_TIMES;
        }
        do {
            retryTimes--;
            boolean success = false;
            try {
                success = lock(redisTemplate, lockKey);
                if (success) {
                    return success;
                }
                TimeUnit.MILLISECONDS.sleep(RETRY_TIME_INTERVAL);
            } catch (Exception e) {
                log.error("exception in get lock", e);
            }
        } while (retryTimes > 0);
        return false;
    }

    /**
     * 带重试的获取lock方法
     *
     * @param redisTemplate
     * @param lockKey
     * @return
     */
    public static boolean lockWithRrtey(RedisTemplate redisTemplate, String lockKey) {
        return lock(redisTemplate, lockKey, DEFAULT_RETRY_TIMES, DEFAULT_EXPIRE_TIME);
    }

    public static void unlock(RedisTemplate redisTemplate, String lockKey) {
        redisTemplate.delete(lockKey);
    }

}

