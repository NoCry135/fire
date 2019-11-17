package com.ca.fire.until.uuid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.Jedis;

import java.util.Arrays;

public class UuidGeneratorFactoryBean implements FactoryBean<UuidGenerator>, InitializingBean {
    private RedisSequenceAllotter allotter;
    private String filePath;
    private String zkServers;
    private Jedis redisClient;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setZkServers(String zkServers) {
        this.zkServers = zkServers;
    }

    public void setRedisClient(Jedis redisClient) {
        this.redisClient = redisClient;
    }

    @Override
    public UuidGenerator getObject() throws Exception {
        UuidGenerator uuidGenerator = new UuidGenerator();
        uuidGenerator.setSequenceAllotter(allotter);
        return uuidGenerator;
    }

    @Override
    public Class<?> getObjectType() {
        return UuidGenerator.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isBlank(zkServers)) {
            throw new RuntimeException("zkServers is null");
        }
        if (redisClient == null) {
            throw new RuntimeException("redisClient is null");
        }

        LocalFileclusterNo clusterNo = new LocalFileclusterNo();
        if (StringUtils.isNotBlank(filePath)) {
            clusterNo.setFilePath(filePath);
        }

        ZookeeperClusterNo zookeeperClusterNo = new ZookeeperClusterNo();
        zookeeperClusterNo.setZkServers(zkServers);
        zookeeperClusterNo.setClusterNoKeeper(clusterNo);
        zookeeperClusterNo.afterPropertiesSet();

        LoopFetchclusterNo loopFetchClusterNo = new LoopFetchclusterNo();
        loopFetchClusterNo.setClusterNoProviders(Arrays.asList(clusterNo, zookeeperClusterNo));

        allotter = new RedisSequenceAllotter();
        allotter.setRedisClient(redisClient);
        allotter.setclusterNo(loopFetchClusterNo);
        allotter.afterPropertiesSet();
    }
}
