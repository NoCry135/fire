package com.ca.fire.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * Guava缓存工具类
 *
 * @author yanhuan1
 */
public abstract class BaseGuavaCache<K, V> {

    /**
     * 缓存空间大小
     */
    private static final Integer MAXIMUM_SIZE = 1000;

    /**
     * 缓存过期时间，单位分钟
     */
    private static final Integer DURATION_MINUTES = 5;

    /**
     * guava缓存
     */
    private LoadingCache<K, V> cache = CacheBuilder.newBuilder()
            .maximumSize(MAXIMUM_SIZE)
            .expireAfterWrite(DURATION_MINUTES, TimeUnit.MINUTES)
            .build(new CacheLoader<K, V>() {
                @Override
                public V load(K k) throws Exception {
                    return get(k);
                }
            });

    /**
     * 从缓存中获取对象
     *
     * @param k key
     * @return value
     */
    public abstract V get(K k);

    public LoadingCache<K, V> getCache() {
        return cache;
    }

    public void setCache(LoadingCache<K, V> cache) {
        this.cache = cache;
    }
}

