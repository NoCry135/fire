package com.ca.fire.cache.support.command.factory.builder;

import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 *
 */
public class JedisPoolWrapper {
    private Pool<Jedis> jedisPool;
    private String server;
    private JedisPoolMode jedisPoolMode;
    private String masterName;

    public JedisPoolWrapper(Pool<Jedis> jedisPool, String server, JedisPoolMode jedisPoolMode) {
        this.jedisPool = jedisPool;
        this.server = server;
        this.jedisPoolMode = jedisPoolMode;
    }

    public JedisPoolWrapper(Pool<Jedis> jedisPool, String server, JedisPoolMode jedisPoolMode, String masterName) {
        this.jedisPool = jedisPool;
        this.server = server;
        this.jedisPoolMode = jedisPoolMode;
        this.masterName = masterName;
    }

    public void destroy() {
        jedisPool.destroy();
    }

    public Jedis getResource() {
        return jedisPool.getResource();
    }

    public Pool<Jedis> getJedisPool() {
        return jedisPool;
    }

    public String getDescription() {
        if (masterName != null && !masterName.isEmpty()) {
            return String.format("mode:%1$s,server:%2$s,masterName:%3$s", jedisPoolMode.name(), server, masterName);
        }
        return String.format("mode:%1$s,server:%2$s", jedisPoolMode.name(), server);
    }

}
