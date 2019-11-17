package com.ca.fire.cache.support.command.primitive;

import com.ca.fire.cache.support.command.CommandExecutor;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 *
 */
public class PrimitiveLpopCommand extends AbstractPrimitiveCommand implements CommandExecutor<String> {
    public PrimitiveLpopCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveLpopCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public String execute(final String key, Object[] args) {
        return execute(new RedisCommandExecutor<String>() {
            @Override
            public String execute(Jedis jedis) {
                return jedis.lpop(key);
            }
        });
    }


}
