package com.ca.fire.util.uuid;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 */
@Component
public class RedisSequenceAllotter extends AbstractSequenceAllotter implements InitializingBean {

    private Jedis redisClient;

    public void setRedisClient(Jedis redisClient) {
        this.redisClient = redisClient;
    }
    private ClusterNo clusterNo;



    public void setclusterNo(ClusterNo clusterNo) {
        this.clusterNo = clusterNo;
    }

    @Override
    protected Long incrAndGet(String seed) {
        return redisClient.incr(seed);
    }

    @Override
    protected Long get(String key) {
        String result = redisClient.get(key);
        if (StringUtils.isBlank(result)) {
            return null;
        }
        return Long.valueOf(result);
    }

    @Override
    protected void save(String key, Long value) {
        Preconditions.checkNotNull(value, "value should be not null!");
        redisClient.set(key, value.toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isBlank(seed)) {
//            seed = clusterNo.getClusterNo();
        }
    }

}
