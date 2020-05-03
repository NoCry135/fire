package com.ca.fire.until.lock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class RedisLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * @param key
     * @param value 当前时线程的操作时间 System.currentTimeMillis() +2000,2000是超时时间
     *              这里不设置expire也不需要在超时后手动删除key,因为会存在并发的线程去删除,造成一个锁失效,结果获得锁去执行,并发操作失败
     * @return
     */
    public Boolean lock(String key, String value) {

        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        String curVal = redisTemplate.opsForValue().get(key);

        if (StringUtils.isNotBlank(curVal) && Long.parseLong(curVal) < System.currentTimeMillis()) {
            String oldVal = redisTemplate.opsForValue().getAndSet(key, value);
            if (StringUtils.isNotBlank(oldVal) && oldVal.equals(curVal)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String curVal = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotBlank(curVal) && curVal.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            LOGGER.error("解锁失败:{}", e);
        }

    }

}
