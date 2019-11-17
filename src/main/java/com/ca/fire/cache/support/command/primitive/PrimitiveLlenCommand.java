package com.ca.fire.cache.support.command.primitive;

import com.ca.fire.cache.support.command.CommandExecutor;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 *
 */
public class PrimitiveLlenCommand extends AbstractPrimitiveCommand implements CommandExecutor<Long> {
    public PrimitiveLlenCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveLlenCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public Long execute(final String key, final Object[] args) {
        return execute(new RedisCommandExecutor<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.llen(key);
            }
        });
    }

}
