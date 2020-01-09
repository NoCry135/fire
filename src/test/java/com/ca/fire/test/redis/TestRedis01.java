package com.ca.fire.test.redis;

import com.ca.fire.domain.bean.Club;
import com.ca.fire.until.ProtostuffUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Date;

public class TestRedis01 {

    @Test
    public void testConnect() {
        Jedis jedis = null;
        try {
//            jedis = new Jedis("39.107.115.53", 6379);
            jedis = new Jedis("39.100.121.226", 6379);
            String set = jedis.set("hello", "java");
            System.out.println(set);
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
    public void testData() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("39.107.115.53", 6379);

            // 输出结果：OK
            jedis.set("hello", "world");
            // 输出结果：world
            jedis.get("hello");
            // 输出结果：1
            jedis.incr("counter");
            // 2.hash
            jedis.hset("myhash", "f1", "v1");
            jedis.hset("myhash", "f2", "v2");
            // 输出结果：{f1=v1, f2=v2}
            jedis.hgetAll("myhash");
            // 3.list
            jedis.rpush("mylist", "1");
            jedis.rpush("mylist", "2");
            jedis.rpush("mylist", "3");
            // 输出结果：[1, 2, 3]
            jedis.lrange("mylist", 0, -1);
            // 4.set
            jedis.sadd("myset", "a");
            jedis.sadd("myset", "b");
            jedis.sadd("myset", "a");
            // 输出结果：[b, a]
            jedis.smembers("myset");
            // 5.zset
            jedis.zadd("myzset", 99, "tom");
            jedis.zadd("myzset", 66, "peter");
            jedis.zadd("myzset", 33, "james");
            // 输出结果：[[["james"],33.0], [["peter"],66.0],

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {

                jedis.close();
            }
        }
    }

    @Test
    public void testSerializable() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("39.107.115.53", 6379);
            String key = "club:1";
// 定义实体对象
            Club club = new Club("AC", "米兰", new Date(), 1);
// 序列化
            byte[] clubBtyes = ProtostuffUtil.serializer(club);
            String set = jedis.set(key.getBytes(), clubBtyes);
            System.out.println(key.getBytes() + " " + set);
            byte[] bytes = jedis.get(key.getBytes());
            Club deserializer = ProtostuffUtil.deserializer(bytes, Club.class);
            System.out.println(deserializer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {

                jedis.close();
            }
        }


    }
}
