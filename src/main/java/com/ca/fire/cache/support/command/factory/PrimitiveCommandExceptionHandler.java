package com.ca.fire.cache.support.command.factory;

import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 *
 */
public interface PrimitiveCommandExceptionHandler {
    /**
     * 命令执行异常处理方法
     *
     * @param currentPool 当前redis连接池
     * @param ex          异常对象
     */
    void handle(Pool<Jedis> currentPool, Exception ex);
}
