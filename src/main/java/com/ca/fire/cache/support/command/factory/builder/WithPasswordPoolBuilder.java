package com.ca.fire.cache.support.command.factory.builder;

import com.ca.fire.cache.support.command.factory.JedisConfig;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

/**
 *
 */
public class WithPasswordPoolBuilder extends NoPasswordPoolBuilder implements JedisPoolBuilder {

    @Override
    protected JedisPoolMode getSupportedMode() {
        return JedisPoolMode.Normal_WithPassword;
    }

    @Override
    protected void check(JedisConfig jedisConfig) {
        super.check(jedisConfig);
        Preconditions.checkArgument(StringUtils.isNotBlank(jedisConfig.getPassword()), "password is blank!");
    }

    @Override
    protected Pool<Jedis> newPool(JedisConfig jedisConfig, HostAndPort hostAndPort) {
        return new JedisPool(getPoolConfig(jedisConfig), hostAndPort.getHost(), hostAndPort.getPort(), jedisConfig.getTimeout(), jedisConfig.getPassword(), jedisConfig.getDatabase());
    }

}
