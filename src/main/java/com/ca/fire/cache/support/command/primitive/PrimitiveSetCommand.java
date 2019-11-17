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
public class PrimitiveSetCommand extends AbstractPrimitiveCommand implements CommandExecutor<String> {
    private static final int LENGTH_OF_ATOMIC_NX_EX_SET = 4;

    public PrimitiveSetCommand(Pool<Jedis> redisClient) {
        super(redisClient);
    }

    public PrimitiveSetCommand(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        super(redisClient, commandExceptionHandler);
    }

    @Override
    public String execute(final String key, final Object[] args) {
        if (ArrayUtils.isEmpty(args)) {
            throw new CacheCommandException("Args is empty when set value for the key:!" + key);
        }
        return execute(new RedisCommandExecutor<String>() {
            @Override
            public String execute(Jedis jedis) {
                if (args.length == LENGTH_OF_ATOMIC_NX_EX_SET) {
                    return jedis.set(key, args[0].toString(), args[1].toString(), args[2].toString(), Long.valueOf(args[3].toString()));
                }
                return jedis.set(key, args[0].toString());
            }
        });
    }

}
