package com.ca.fire.cache.support.command.primitive;

import com.ca.fire.cache.support.command.CacheCommandException;
import com.ca.fire.cache.support.command.CommandExecutor;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import org.apache.commons.lang3.ArrayUtils;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * @author
 */
public class PrimitiveHgetCommand extends AbstractPrimitiveCommand implements CommandExecutor<String> {

    public PrimitiveHgetCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveHgetCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public String execute(final String key, final Object[] args) {
        if (ArrayUtils.isEmpty(args)) {
            throw new CacheCommandException("Args is empty when hget value for the key:!" + key);
        }
        return execute(new RedisCommandExecutor<String>() {
            @Override
            public String execute(Jedis jedis) {
                return jedis.hget(key, args[0].toString());
            }
        });
    }
}
