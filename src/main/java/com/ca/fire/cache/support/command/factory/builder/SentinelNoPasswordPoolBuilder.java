package com.ca.fire.cache.support.command.factory.builder;

import com.ca.fire.cache.support.command.factory.JedisConfig;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SentinelNoPasswordPoolBuilder extends NoPasswordPoolBuilder implements JedisPoolBuilder {

    @Override
    protected JedisPoolMode getSupportedMode() {
        return JedisPoolMode.Sentinel_WithoutPassword;
    }

    @Override
    public List<JedisPoolWrapper> build(JedisConfig jedisConfig) {
        check(jedisConfig);
        List<JedisPoolWrapper> jedisPools = new ArrayList<JedisPoolWrapper>(1);
        Pool<Jedis> jedisPool = newPool(jedisConfig, null);
        jedisPools.add(new JedisPoolWrapper(jedisPool, jedisConfig.getSentinelServers(), getSupportedMode(), jedisConfig.getMasterName()));
        return jedisPools;
    }

    @Override
    protected void check(JedisConfig jedisConfig) {
        Preconditions.checkArgument(jedisConfig != null, "jedisConfig is null");
        Preconditions.checkArgument(StringUtils.isNotBlank(jedisConfig.getMasterName()), "masterName is blank!");
        Preconditions.checkArgument(StringUtils.isNotBlank(jedisConfig.getSentinelServers()), "sentinelServers is blank!");
    }

    @Override
    protected Pool<Jedis> newPool(JedisConfig jedisConfig, HostAndPort hostAndPort) {
        return new JedisSentinelPool(jedisConfig.getMasterName(), getServerSet(jedisConfig.getSentinelServers()), getPoolConfig(jedisConfig), jedisConfig.getTimeout(), null, jedisConfig.getDatabase());
    }

}
