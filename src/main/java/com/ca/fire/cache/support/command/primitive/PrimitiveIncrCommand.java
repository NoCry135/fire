package com.ca.fire.cache.support.command.primitive;

import com.ca.fire.cache.support.command.CommandExecutor;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * @author
 */
public class PrimitiveIncrCommand extends AbstractPrimitiveCommand implements CommandExecutor<Long> {

    public PrimitiveIncrCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveIncrCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public Long execute(final String key, Object[] args) {
        return execute(new RedisCommandExecutor<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.incr(key);
            }
        });
    }

}
