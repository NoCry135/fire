package com.ca.fire.cache.support.command.factory.builder;

import com.ca.fire.cache.support.command.factory.JedisConfig;

import java.util.List;

/**
 *
 */
public interface JedisPoolBuilder {
    /**
     * builder是否支持指定模式
     *
     * @param mode 指定模式
     * @return 支持返回true，否则返回false
     */
    boolean support(int mode);

    /**
     * 创建JedisPool列表
     *
     * @param jedisConfig jedis配置信息
     * @return JedisPool列表
     */
    List<JedisPoolWrapper> build(JedisConfig jedisConfig);
}
