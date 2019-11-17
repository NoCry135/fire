package com.ca.fire.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCommands;

@Component
public class MyJedisClinet {

//    @Resource(name = "jedisCommands")
    private JedisCommands redisClient;


    public JedisCommands getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(JedisCommands redisClient) {
        this.redisClient = redisClient;
    }
}


