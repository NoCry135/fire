package com.ca.fire.cache.support.command.primitive;

import com.ca.fire.cache.support.command.CacheCommandException;
import com.ca.fire.cache.support.command.CommandExecutor;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import org.apache.commons.lang3.ArrayUtils;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import java.util.List;

/**
 * @author
 */
public class PrimitiveHmgetCommand extends AbstractPrimitiveCommand implements CommandExecutor<List<String>> {

    public PrimitiveHmgetCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveHmgetCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public List<String> execute(final String key, final Object[] args) {
        if (ArrayUtils.isEmpty(args)) {
            throw new CacheCommandException("Args is empty when hget value for the key:!" + key);
        }
        return execute(new RedisCommandExecutor<List<String>>() {
            @Override
            public List<String> execute(Jedis jedis) {
                return jedis.hmget(key, (String[])args[0]);
            }
        });
    }
}
