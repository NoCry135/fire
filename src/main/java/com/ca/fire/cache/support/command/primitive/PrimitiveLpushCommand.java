package com.ca.fire.cache.support.command.primitive;

import com.ca.fire.cache.support.command.CommandExecutor;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 *
 */
public class PrimitiveLpushCommand extends AbstractPrimitiveCommand implements CommandExecutor<Long> {
    public PrimitiveLpushCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveLpushCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public Long execute(final String key, final Object[] args) {
        return execute(new RedisCommandExecutor<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                if (args == null || args.length == 0) {
                    return null;
                }
                return jedis.lpush(key, (String[]) args[0]);
            }
        });
    }

}
