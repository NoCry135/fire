package com.ca.fire.cache.support.command.factory;

import com.ca.fire.cache.support.command.CacheCommandException;
import com.ca.fire.cache.support.command.CommandExecutor;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 */
public abstract class AbstractNamedCommandFactory implements CommandFactory {
    private final Map<String, CommandExecutor> commandExecutorCache;
    protected String basePackage;

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getBasePackage() {
        if (StringUtils.isBlank(this.basePackage)) {
            return getDefaultBasePackage();
        }
        return basePackage;
    }

    protected String getDefaultBasePackage() {
        return null;
    }

    AbstractNamedCommandFactory() {
        commandExecutorCache = new HashMap<String, CommandExecutor>();
    }

    @Override
    public CommandExecutor create(String commandName, Object[] commandArgs) {
        String cacheKey = getCacheKey(commandName, commandArgs);
        if (commandExecutorCache.containsKey(cacheKey)) {
            return commandExecutorCache.get(cacheKey);
        }
        return initAndCache(cacheKey, commandName, commandArgs);
    }

    protected String getCacheKey(String commandName, Object[] commandArgs) {
        return commandName;
    }

    protected synchronized CommandExecutor initAndCache(String cacheKey, String commandName, Object[] commandArgs) {
        String className = getTargetCommandExecutorClassName(commandName, commandArgs);
        CommandExecutor commandExecutor = getCommandExecutor(commandName, className);
        commandExecutorCache.put(cacheKey, commandExecutor);
        return commandExecutor;
    }

    protected String getTargetCommandExecutorClassName(String commandName, Object[] commandArgs) {
        return MessageFormat.format(StringUtils.join(getBasePackage(), getTargetCommandExecutorNameFormat()), uppercaseFirstChar(commandName));
    }

    private String uppercaseFirstChar(String source) {
        String firstChar = source.substring(0, 1);
        return StringUtils.join(firstChar.toUpperCase(), source.substring(1));
    }

    /**
     * 获取格式化命令名
     *
     * @return 命令名
     */
    protected abstract String getTargetCommandExecutorNameFormat();

    protected Class[] getConstructParameterTypes() {
        return null;
    }

    protected Object[] getConstructParameters() {
        return null;
    }

    protected CommandExecutor getCommandExecutor(String commandName, String className) {
        Class<?> objectClass;
        try {
            objectClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new CacheCommandException(MessageFormat.format("Command [{0}] is not support yet!", commandName), e);
        }
        try {
            Object object = newInstance(objectClass);
            if (object instanceof CommandExecutor) {
                return (CommandExecutor) object;
            }
            throw new CacheCommandException(MessageFormat.format("Instance from {0} is not implements CommandExecutor!", className));
        } catch (Exception e) {
            throw new CacheCommandException(MessageFormat.format("Get instance for {0} error!", className), e);
        }
    }

    private Object newInstance(Class<?> objectClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (getConstructParameterTypes() == null) {
            return objectClass.newInstance();
        }
        Constructor<?> constructor = objectClass.getConstructor(getConstructParameterTypes());
        return constructor.newInstance(getConstructParameters());
    }

}
