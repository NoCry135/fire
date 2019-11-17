//package com.ca.fire.until;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
///**
// * Redis缓存操作类
// *
// * @author: wshuang
// * @time: 15-8-13 下午5:46
// * @version: 1.0
// */
//
//public class RedisUtil {
//
//    private final static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
//
//    private Cluster redisClient;
//
//    public void setRedisClient(Cluster redisClient) {
//        this.redisClient = redisClient;
//    }
//
//    @Resource
//    private DefaultObjectSerializer serializer;
//
//    public final static int DEFAULT_CACHED_EXPIRED_TIME = 60 * 30; // 默认缓存半个小时
//
//    /**
//     * 存放String类型的值
//     *
//     * @param key
//     * @param obj
//     * @throws Exception
//     */
//    public void put(String key, Object obj) {
//        try {
//            put(key, DEFAULT_CACHED_EXPIRED_TIME, obj);
//        } catch (Exception e) {
//            logger.error("RedisUtil.put error----------->Key:{}", key, e);
//        }
//    }
//
//    /**
//     * 存放String类型的值
//     *
//     * @param key
//     * @param obj
//     * @param expires 缓存过期时间,单位:秒
//     */
//    public void put(String key, int expires, Object obj) {
//        CallerInfo callInfo= UmpUtil.startUmp(UmpKeyConstant.redis_put_key,false,true);
//        try {
//            redisClient.setEx(key.getBytes(), serializer.serialize(obj), expires, TimeUnit.SECONDS);
//        } catch (Exception e) {
//            UmpUtil.endUmp(callInfo);
//            logger.error("RedisUtil.put error----------->Key:{}", key, e);
//        }
//        UmpUtil.endUmp(callInfo);
//    }
//
//    public boolean setnx(String key, int expires, Object obj) {
//        // 如果缓存出错,认为成功
//        Boolean success = true;
//        try {
//            success = redisClient.setNX(key.getBytes(), serializer.serialize(obj));
//            if (success){
//                setExpire(key, expires);
//            }
//        } catch (Exception e) {
//            logger.error("RedisUtil.setnx error----------->Key:{}", key, e);
//        }
//        return success;
//    }
//
//    /**
//     * 获取String类型的值
//     *
//     * @param key key值
//     * @return 取得对应值
//     */
//    public Object get(String key) {
//        CallerInfo callInfo= UmpUtil.startUmp(UmpKeyConstant.redis_get_key,false,true);
//        long startTime = System.currentTimeMillis();
//        if (key == null){
//            UmpUtil.endUmp(callInfo);
//            return null;
//        }
//        Object obj = null;
//        byte[] buf;
//        try {
//            buf = redisClient.get(key.getBytes());
//            if (buf == null) {
//                logger.info("Not found key : {} in Redis", key);
//                UmpUtil.endUmp(callInfo);
//                return buf;
//            }
//            obj = serializer.deserialize(buf);
//        } catch (Exception e) {
//            logger.error("deserialize error! Key:{}", key, e);
//        }
//        UmpUtil.endUmp(callInfo);
//        return obj;
//    }
//
//
//
//    public long getTimesToLive(String key) {
//        if (key == null){
//            return 0L;
//        }
//        try {
//            return redisClient.ttl(key.getBytes());
//        } catch (Exception e) {
//            logger.error("Getting TTL by Key : {} in Redis error!", key, e);
//        }
//        return 0L;
//    }
//
//    /**
//     * 设置缓存 key过期时间
//     * @param key
//     * @param seconds
//     */
//    public void setExpire(String key, int seconds) {
//        redisClient.expire(key.getBytes(), seconds, TimeUnit.SECONDS);
//    }
//
//    /**
//     * 判断缓存中是否存在此key
//     *
//     * @param key key值
//     * @return true存在，false不存在
//     */
//    public boolean exists(String key) {
//        boolean flag = false;
//        try {
//            flag = redisClient.exists(key.getBytes());
//        } catch (Exception e) {
//            logger.error("Redis判断指定Key:{}是否存在发生异常", key, e);
//        }
//        return flag;
//    }
//
//    /**
//     * 根据key值删除缓存内容
//     *
//     * @param key key值
//     */
//    public void deleteByKey(String key) {
//        try {
//            if (redisClient.exists(key.getBytes())) {
//                redisClient.del(key.getBytes());
//            }
//        } catch (Exception e) {
//            logger.error("Redis删除Key:{}异常", key, e);
//        }
//    }
//
//    /**
//     * 获取传入key的类别——string、hash、zset等
//     *
//     * @param key
//     * @return
//     */
//    public DataType getType(String key) {
//        try {
//            if (redisClient.exists(key.getBytes())) {
//                return redisClient.type(key.getBytes());
//            }
//        } catch (Exception e) {
//            logger.error("Redis根据Key:{}获取对象类型异常", key, e);
//        }
//        return null;
//    }
//
//    public long incrementBy(String key, int val, int expires) {
//        long nv = -1L;
//        try {
//            nv = redisClient.incrBy(key.getBytes(), val);
//            setExpire(key, expires);
//        } catch (Exception e) {
//            logger.error("Calling Redis.INCRBY by Key:{}", key, e);
//        }
//        return nv;
//    }
//
//    public long decrementBy(String key, int val) {
//        try {
//            return redisClient.decrBy(key.getBytes(), val);
//        } catch (Exception e) {
//            logger.error("Calling Redis.DECRBY by Key:{}", key, e);
//        }
//        return -1L;
//    }
//    public boolean hSet(String cachekey, String key, String value) {
//        try {
//            return  redisClient.hSet(cachekey.getBytes(),key.getBytes(),value.getBytes());
//        } catch (Exception e) {
//            logger.error("Calling Redis.HSET by Key:{}", key, e);
//        }
//        return false;
//    }
//    public boolean hSetObj(String cachekey, String key, Object obj) {
//        try {
//            return  redisClient.hSet(cachekey.getBytes(),key.getBytes(),serializer.serialize(obj));
//        } catch (Exception e) {
//            logger.error("Calling Redis.HSET by Key:{}", key, e);
//        }
//        return false;
//    }
//    public Object hGetObj(String cachekey, String key) {
//        Object obj=null;
//        byte[] json=null;
//        try {
//            json=redisClient.hGet(cachekey.getBytes(),key.getBytes());
//            obj=serializer.deserialize(json);
//        } catch (Exception e) {
//            logger.error("Calling Redis.HGET by Key:{}", key, e);
//        }
//        return obj;
//    }
//    public String hGet(String cachekey, String key) {
//        String json=null;
//        try {
//            json=redisClient.hGet(cachekey,key);
//        } catch (Exception e) {
//            logger.error("Calling Redis.HGET by Key:{}", key, e);
//        }
//        return json;
//    }
//    public Set key(String cachekey) {
//        Set json=null;
//        try {
//            json=redisClient.keys(cachekey);
//        } catch (Exception e) {
//            logger.error("Calling Redis.keys by Key:{}",cachekey, e);
//        }
//        return json;
//    }
//    public Map<String,Object> hGetAllObj(String cachekey) {
//        Map<String,Object> retMap=new HashMap<>();
//        Map<byte[],byte[]> map=null;
//        try {
//            map=redisClient.hGetAll(cachekey.getBytes());
//            if(map!=null&&map.size()>0){
//                for(byte[] byteAttr:map.keySet()){
//                    retMap.put(new String(byteAttr),serializer.deserialize(map.get(byteAttr)));
//                }
//            }
//
//        } catch (Exception e) {
//            logger.error("Calling Redis.HGET by Key:{}", cachekey, e);
//        }
//        return retMap;
//    }
//    public Map hGetAll(String cachekey) {
//        Map map=null;
//        try {
//            map=redisClient.hGetAll(cachekey);
//        } catch (Exception e) {
//            logger.error("Calling Redis.HGET by Key:{}", cachekey, e);
//        }
//        return map;
//    }
//}
