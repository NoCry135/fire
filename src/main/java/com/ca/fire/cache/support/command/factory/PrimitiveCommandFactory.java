package com.ca.fire.cache.support.command.factory;

import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 *
 */
public class PrimitiveCommandFactory extends AbstractNamedCommandFactory {
    private static final String COMMAND_BASE_PACKAGE = "com.cache.support.command.primitive.";
    private static final String COMMAND_CLASS_NAME_FORMAT = "Primitive{0}Command";
    private final Pool<Jedis> redisClient;
    private PrimitiveCommandExceptionHandler commandExceptionHandler;

    public PrimitiveCommandFactory(Pool<Jedis> redisClient) {
        this.redisClient = redisClient;
    }

    public PrimitiveCommandFactory(Pool<Jedis> redisClient, PrimitiveCommandExceptionHandler commandExceptionHandler) {
        this.redisClient = redisClient;
        this.commandExceptionHandler = commandExceptionHandler;
    }

    @Override
    protected Class[] getConstructParameterTypes() {
        if (commandExceptionHandler != null) {
            return new Class[]{Pool.class, PrimitiveCommandExceptionHandler.class};
        }
        return new Class[]{Pool.class};
    }

    @Override
    protected Object[] getConstructParameters() {
        if (commandExceptionHandler != null) {
            return new Object[]{redisClient, commandExceptionHandler};
        }
        return new Object[]{redisClient};
    }

    @Override
    protected String getTargetCommandExecutorNameFormat() {
        return COMMAND_CLASS_NAME_FORMAT;
    }

    @Override
    protected String getDefaultBasePackage() {
        return COMMAND_BASE_PACKAGE;
    }
}
