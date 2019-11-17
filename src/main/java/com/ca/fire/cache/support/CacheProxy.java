package com.ca.fire.cache.support;

import com.ca.fire.cache.support.command.CommandExecutor;
import com.ca.fire.cache.support.command.factory.CommandFactory;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author
 */
public class CacheProxy implements InvocationHandler {
    private final CommandFactory commandFactory;

    public CacheProxy(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        CommandExecutor commandExecutor = commandFactory.create(method.getName(), args);
        if (ArrayUtils.isEmpty(args)) {
            throw new IllegalArgumentException("Args is empty!");
        }
        String key = args[0].toString();
        if (args.length == 1) {
            return commandExecutor.execute(key, args);
        }
        return commandExecutor.execute(key, ArrayUtils.subarray(args, 1, args.length));
    }

}
