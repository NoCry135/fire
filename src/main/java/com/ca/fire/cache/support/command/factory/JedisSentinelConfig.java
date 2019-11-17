package com.ca.fire.cache.support.command.factory;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 *
 */
public class JedisSentinelConfig {
    private String masterName;
    private String servers;
    private String password;
    private GenericObjectPoolConfig poolConfig;

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
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
}
