package com.ca.fire.cache.support.command.primitive;

import com.ca.fire.cache.support.command.CacheCommandException;
import com.ca.fire.cache.support.command.CommandExecutor;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.ArrayUtils;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 *
 */
public class PrimitiveSetexCommand extends AbstractPrimitiveCommand implements CommandExecutor<String> {

    public PrimitiveSetexCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveSetexCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public String execute(final String key, final Object[] args) {
        if (ArrayUtils.isEmpty(args)) {
            throw new CacheCommandException("Args is empty when expire the key:!" + key);
        }
        Preconditions.checkArgument(args.length > 1, "args number must greater than 1!");
        return execute(new RedisCommandExecutor<String>() {
            @Override
            public String execute(Jedis jedis) {
                return jedis.setex(key, Integer.valueOf(args[0].toString()), args[1].toString());
            }
        });
    }
}
