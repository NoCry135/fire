package com.ca.fire.cache.support.command.factory.builder;

import java.util.List;

/**
 *
 */
public interface JedisPoolBuilderFactory {
    /**
     * 获取JedisPoolBuilder列表
     *
     * @return JedisPoolBuilder列表
     */
    List<JedisPoolBuilder> create();
}
