package com.ca.fire.cache.support.command.primitive;

import com.ca.fire.cache.support.command.CommandExecutor;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 *
 */
public class PrimitiveExistsCommand extends AbstractPrimitiveCommand implements CommandExecutor<Boolean> {
    public PrimitiveExistsCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveExistsCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public Boolean execute(final String key, Object[] args) {
        return execute(new RedisCommandExecutor<Boolean>() {
            @Override
            public Boolean execute(Jedis jedis) {
                return jedis.exists(key);
            }
        });
    }
}
