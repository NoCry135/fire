package com.ca.fire.cache.support.command.primitive;

import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 *
 */
public abstract class AbstractPrimitiveCommand {
    protected Pool<Jedis> redisClient;
    private PrimitiveCommandExceptionHandler commandExceptionHandler;

    public void setRedisClient(Pool<Jedis> redisClient) {
        this.redisClient = redisClient;
    }

    public AbstractPrimitiveCommand(Pool<Jedis> redisClient) {
        this.redisClient = redisClient;
    }

    public AbstractPrimitiveCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        this.redisClient = redisClient;
        this.commandExceptionHandler = commandExceptionHandler;
    }

    protected <T> T execute(RedisCommandExecutor<T> executor) {
        Jedis jedis = redisClient.getResource();
        T result;
        try {
            result = executor.execute(jedis);
        } catch (Exception ex) {
            if (commandExceptionHandler != null) {
                commandExceptionHandler.handle(redisClient, ex);
            }
            throw new RuntimeException(ex);
        } finally {
            jedis.close();
        }
        return result;
    }

    interface RedisCommandExecutor<T> {
        /**
         * 执行redis操作
         *
         * @param jedis redis客户端对象
         * @return 操作完后具体返回值
         */
        T execute(Jedis jedis);
    }
}
