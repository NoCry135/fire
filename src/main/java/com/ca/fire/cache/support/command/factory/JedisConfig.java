package com.ca.fire.cache.support.command.factory;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * @author
 */
public class JedisConfig {
    private String servers;
    private String masterName;
    private String sentinelServers;
    private String password;
    private GenericObjectPoolConfig poolConfig;
    private int timeout = Protocol.DEFAULT_TIMEOUT;
    private int database = Protocol.DEFAULT_DATABASE;

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getSentinelServers() {
        return sentinelServers;
    }

    public void setSentinelServers(String sentinelServers) {
        this.sentinelServers = sentinelServers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GenericObjectPoolConfig getPoolConfig() {
        return poolConfig;
    }

    public void setPoolConfig(GenericObjectPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }
}
