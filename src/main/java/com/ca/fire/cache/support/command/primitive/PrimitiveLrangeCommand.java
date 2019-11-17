package com.ca.fire.cache.support.command.primitive;

import com.ca.fire.cache.support.command.CommandExecutor;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import java.util.List;

/**
 *
 */
public class PrimitiveLrangeCommand extends AbstractPrimitiveCommand implements CommandExecutor<List<String>> {
    private static final int MIN_ARGS_LENGTH = 2;

    public PrimitiveLrangeCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveLrangeCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public List<String> execute(final String key, final Object[] args) {
        return execute(new RedisCommandExecutor<List<String>>() {
            @Override
            public List<String> execute(Jedis jedis) {
                if (args == null || args.length < MIN_ARGS_LENGTH) {
                    return null;
                }
                return jedis.lrange(key, Long.valueOf(args[0].toString()), Long.valueOf(args[1].toString()));
            }
        });
    }

}
