package com.ca.fire.cache.support;

import com.ca.fire.cache.support.command.factory.CommandFactory;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandExceptionHandler;
import com.ca.fire.cache.support.command.factory.PrimitiveCommandFactory;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.util.Pool;

/**
 * @author
 */
public class CacheProxyFactoryBean implements FactoryBean<JedisCommands>, InitializingBean, DisposableBean {
    private static final String NORMAL_ENV_IDENTITY = "JDREDIS";
    private static final String IDC_ENV_IDENTITY = "JIM";
    private Class[] cacheInterfaceTypes;
    private CommandFactory commandFactory;
    private String type;
    private String jimUrl;
    private int jimTimeout;
    private Pool<Jedis> jedisPool;
    private PrimitiveCommandExceptionHandler primitiveCommandExceptionHandler;

    public void setType(String type) {
        this.type = type;
    }

    public void setJimUrl(String jimUrl) {
        this.jimUrl = jimUrl;
    }

    public void setJimTimeout(int jimTimeout) {
        this.jimTimeout = jimTimeout;
    }

    public void setJedisPool(Pool<Jedis> jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void setPrimitiveCommandExceptionHandler(PrimitiveCommandExceptionHandler primitiveCommandExceptionHandler) {
        this.primitiveCommandExceptionHandler = primitiveCommandExceptionHandler;
    }

    @Override
    public JedisCommands getObject() throws Exception {
        return new CacheProxyFactory<JedisCommands>(cacheInterfaceTypes).createProxy(commandFactory);
    }

    @Override
    public Class<?> getObjectType() {
        return JedisCommands.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isBlank(type)) {
            type = NORMAL_ENV_IDENTITY;
        }
        checkProperties();
        if (cacheInterfaceTypes == null) {
            cacheInterfaceTypes = new Class[]{JedisCommands.class};
        }
        if (commandFactory == null) {
            commandFactory = createCommandFactory();
        }
    }

    private void checkProperties() {
        if (isIdcEnv()) {
            if (StringUtils.isBlank(jimUrl)) {
                throw new IllegalStateException("jimUrl is not set!");
            }
        } else {
            Preconditions.checkArgument(jedisPool != null, "jedisPool is null!");
        }
    }

    private CommandFactory createCommandFactory() {

        if (primitiveCommandExceptionHandler == null && jedisPool instanceof PrimitiveCommandExceptionHandler) {
            primitiveCommandExceptionHandler = (PrimitiveCommandExceptionHandler) jedisPool;
        }
        if (primitiveCommandExceptionHandler != null) {
            return new PrimitiveCommandFactory(jedisPool, primitiveCommandExceptionHandler);
        }
        return new PrimitiveCommandFactory(jedisPool);
    }

    private boolean isIdcEnv() {
        return StringUtils.equalsIgnoreCase(IDC_ENV_IDENTITY, type);
    }


    @Override
    public void destroy() throws Exception {

        if (jedisPool != null) {
            jedisPool.destroy();
        }
    }

}
