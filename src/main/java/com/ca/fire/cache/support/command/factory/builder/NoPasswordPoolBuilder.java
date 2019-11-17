package com.ca.fire.cache.support.command.factory.builder;

import com.ca.fire.cache.support.command.factory.JedisConfig;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class NoPasswordPoolBuilder extends AbstractJedisPoolsBuilder implements JedisPoolBuilder {


    @Override
    protected JedisPoolMode getSupportedMode() {
        return JedisPoolMode.Normal_WithoutPassword;
    }

    @Override
    public List<JedisPoolWrapper> build(JedisConfig jedisConfig) {
        check(jedisConfig);
        Set<String> serverSet = getServerSet(jedisConfig.getServers());
        List<JedisPoolWrapper> jedisPools = new ArrayList<JedisPoolWrapper>(serverSet.size());
        for (String server : serverSet) {
            HostAndPort hostAndPort = HostAndPort.parseString(server);
            Pool<Jedis> jedisPool = newPool(jedisConfig, hostAndPort);
            jedisPools.add(new JedisPoolWrapper(jedisPool, server, getSupportedMode()));
        }
        return jedisPools;
    }

    protected void check(JedisConfig jedisConfig) {
        Preconditions.checkArgument(jedisConfig != null, "jedisConfig is null");
        Preconditions.checkArgument(StringUtils.isNotBlank(jedisConfig.getServers()), "servers is blank!");
    }

    protected Pool<Jedis> newPool(JedisConfig jedisConfig, HostAndPort hostAndPort) {
        return new JedisPool(getPoolConfig(jedisConfig), hostAndPort.getHost(), hostAndPort.getPort(), jedisConfig.getTimeout(), null, jedisConfig.getDatabase());
    }

}
