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
public class PrimitiveHsetCommand extends AbstractPrimitiveCommand implements CommandExecutor<Long> {

    public PrimitiveHsetCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveHsetCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public Long execute(final String key, final Object[] args) {
        if (ArrayUtils.isEmpty(args)) {
            throw new CacheCommandException("Args is empty when hget value for the key:!" + key);
        }
        return execute(new RedisCommandExecutor<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.hset(key, args[0].toString(), args[1].toString());
            }
        });
    }
}
