package com.ca.fire.cache.support.command.primitive;

import com.ca.fire.cache.support.command.CommandExecutor;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import java.util.Map;

/**
 * @author
 */
public class PrimitiveHgetAllCommand extends AbstractPrimitiveCommand implements CommandExecutor<Map<String, String>> {

    public PrimitiveHgetAllCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveHgetAllCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public Map<String, String> execute(final String key, final Object[] args) {
        return execute(new RedisCommandExecutor<Map<String, String>>() {
            @Override
            public Map<String, String> execute(Jedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }
}
