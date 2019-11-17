package com.ca.fire.cache.support.command.primitive;

import com.ca.fire.cache.support.command.CommandExecutor;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 *
 */
public class PrimitiveRpopCommand extends AbstractPrimitiveCommand implements CommandExecutor<String> {
    public PrimitiveRpopCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveRpopCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public String execute(final String key, final Object[] args) {
        return execute(new RedisCommandExecutor<String>() {
            @Override
            public String execute(Jedis jedis) {
                if (args == null || args.length == 0) {
                    return null;
                }
                return jedis.rpop(key);
            }
        });
    }

}
