package com.ca.fire.test.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.Arrays;
import java.util.List;

public class TestJedisPool {

    // common-pool连接池配置，这里使用默认配置，后面小节会介绍具体配置说明
    GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
    // 初始化Jedis连接池
    JedisPool jedisPool = new JedisPool(poolConfig, "39.107.115.53", 6379);

    static {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
// 设置最大连接数为默认值的5倍
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 5);
// 设置最大空闲连接数为默认值的3倍
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 3);
// 设置最小空闲连接数为默认值的2倍
        poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE * 2);
// 设置开启jmx功能
        poolConfig.setJmxEnabled(true);
// 设置连接池没有连接后客户端的最大等待时间(单位为毫秒)
        poolConfig.setMaxWaitMillis(3000);
    }

    @Test
    public void testConnect() {
        Jedis jedis = null;
        try {
// 1. 从连接池获取jedis对象
            jedis = jedisPool.getResource();
// 2. 执行操作
            String hello = jedis.get("hello");
            System.out.println(hello);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {

                jedis.close();
            }
        }

    }


    @Test
    public void testPipeLineDel() {
        Jedis jedis = null;
        try {
// 1. 从连接池获取jedis对象
            jedis = jedisPool.getResource();
// 2. 执行操作
            // 1)生成pipeline对象
            Pipeline pipeline = jedis.pipelined();
// 2)pipeline执行命令，注意此时命令并未真正执行
            List<String> keys = Arrays.asList("k1", "k2");
            for (String key : keys) {
                pipeline.del(key);
            }
// 3)执行命令
            pipeline.sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {

                jedis.close();
            }
        }

    }

    @Test
    public void testPipeLine() {
        Jedis jedis = null;
        try {
// 1. 从连接池获取jedis对象
            jedis = jedisPool.getResource();
// 2. 执行操作
            // 1)生成pipeline对象
            Pipeline pipeline = jedis.pipelined();
            pipeline.set("hello", "world");
            pipeline.incr("counter");
            List<Object> resultList = pipeline.syncAndReturnAll();
            for (Object object : resultList) {
                System.out.println(object);
            }
            pipeline.sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {

                jedis.close();
            }
        }

    }

    /**
     * evalsha函数用来执行脚本的SHA1校验和，它需要三个参数：
     * ·scriptSha：脚本的SHA1。
     * ·keyCount：键的个数。
     * ·params：相关参数KEYS和ARGV。
     */
    @Test
    public void testLua() {
        Jedis jedis = null;
        try {
// 1. 从连接池获取jedis对象
            jedis = jedisPool.getResource();
// 2. 执行操作
            String key = "hello";
            String script = "return redis.call('get',KEYS[1])";
//            Object result = jedis.eval(script, 1, key);
// 打印结果为world
//            System.out.println(result);

            String scriptSha = jedis.scriptLoad(script);
            Object result = jedis.evalsha(scriptSha, 1, key);
// 打印结果为world
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {

                jedis.close();
            }
        }

    }
}
