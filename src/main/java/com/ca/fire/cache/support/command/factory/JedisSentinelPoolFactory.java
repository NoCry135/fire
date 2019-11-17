package com.ca.fire.cache.support.command.factory;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class JedisSentinelPoolFactory {
    private static final String SERVER_SEPARATOR = ",";
    private static final long MIN_EVICTABLE_IDLE_TIME_MILLIS = 180000;
    private static final long TIME_BETWEEN_EVICTION_RUNS_MILLIS = 60000;

    public static JedisSentinelPool create(String masterName, String servers) {
        return new JedisSentinelPool(masterName, getServerSet(servers), defaultPoolConfig());
    }

    public static JedisSentinelPool create(String masterName, String servers, String password) {
        if (StringUtils.isBlank(password)) {
            return create(masterName, servers);
        }
        return new JedisSentinelPool(masterName, getServerSet(servers), defaultPoolConfig(), password);
    }

    public static JedisSentinelPool create(String masterName, String servers, JedisPoolConfig poolConfig) {
        return new JedisSentinelPool(masterName, getServerSet(servers), poolConfig);
    }

    public static JedisSentinelPool create(String masterName, String servers, JedisPoolConfig poolConfig, String password) {
        if (StringUtils.isBlank(password)) {
            return create(masterName, servers, poolConfig);
        }
        return new JedisSentinelPool(masterName, getServerSet(servers), poolConfig, password);
    }

    private static Set<String> getServerSet(String servers) {
        Preconditions.checkArgument(StringUtils.isNotBlank(servers), "servers is blank!");
        String[] serverArr = servers.split(SERVER_SEPARATOR);
        return new HashSet<String>(Arrays.asList(serverArr));
    }

    private static GenericObjectPoolConfig defaultPoolConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(MIN_EVICTABLE_IDLE_TIME_MILLIS);
        poolConfig.setSoftMinEvictableIdleTimeMillis(MIN_EVICTABLE_IDLE_TIME_MILLIS);
        poolConfig.setNumTestsPerEvictionRun(poolConfig.getMaxTotal());
        poolConfig.setTimeBetweenEvictionRunsMillis(TIME_BETWEEN_EVICTION_RUNS_MILLIS);
        return poolConfig;
    }
}
