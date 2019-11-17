package com.ca.fire.cache.support;


import com.ca.fire.cache.support.command.factory.CommandFactory;

import java.lang.reflect.Proxy;

/**
 * @author
 */
public class CacheProxyFactory<T> {
    private Class<T>[] interfaceTypes;

    public CacheProxyFactory(Class<T>[] interfaceTypes) {
        this.interfaceTypes = interfaceTypes;
    }

    public T createProxy(CommandFactory commandFactory) {
        CacheProxy cacheProxy = new CacheProxy(commandFactory);
        return (T) Proxy.newProxyInstance(interfaceTypes[0].getClassLoader(), interfaceTypes, cacheProxy);
    }

}
