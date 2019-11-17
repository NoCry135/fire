package com.ca.fire.until;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 */
//@Component
public final class ServerInstanceLockService {
    private final static Logger logger = LoggerFactory.getLogger(ServerInstanceLockService.class);

    @Resource
    private JedisCommands jedisCommands;
    private String redisKey = "TaskAssignServerInstanceLockService";

    /**
     * 过期时间，以秒为单位
     */
    private int expirePeriod = 300;
    /**
     * 内部线程，初始化以后定时刷新
     */
    private Thread threadInner;
    private boolean hasLock;

    /**
     * 一个超时周期内的心跳次数
     */
    private int heartBeatCount = 5;


    /**
     * 初始化
     */
    public void init() {
        threadInner = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        hasLock = trySeizeTheLock();
                    } catch (Exception ex) {
                        logger.error("夺取服务器锁发生异常", ex);
                    }
                    try {
                        logger.debug("休息" + (expirePeriod / heartBeatCount) + "秒");
                        Thread.sleep(expirePeriod / heartBeatCount * 1000);
                    } catch (InterruptedException ex) {
                        logger.error("休息时异常中断", ex);
                    } catch (Exception ex) {
                        logger.error("休息时异常", ex);
                    }
                }
            }
        });
        threadInner.start();
    }

    /**
     * 获取服务器实例排他锁
     *
     * @return
     */
    public boolean getServerInstanceLock() {
        logger.debug("getServerInstanceLock");
        return hasLock;
    }

    /**
     * 尝试夺取实例锁
     *
     * @return
     */
    private boolean trySeizeTheLock() {
        try {
            synchronized (Class.class) {
                String serverGroup = System.getProperty("server.group");
                String groupRedisKeyLock = redisKey + "serverip." + serverGroup;
                String groupRedisKey = redisKey + serverGroup;
                String serverIp = System.getProperty("server.ip");
                if (serverIp == null || serverIp.equals("")) {
                    logger.warn("未获取到tomcat启动参数，请设置。格式为：-Dserver.ip=127.0.0.1");
                    return false;
                }
                String serverPort = System.getProperty("server.port");
                String serverIdentity = serverIp + ":" + serverPort;
                String resultSet = jedisCommands.set(groupRedisKeyLock, serverIdentity, "NX", "EX", expirePeriod);
                logger.info(MessageFormat.format("groupRedisKeyLock:{0},serverIdentity:{1},resultSet:{2}", groupRedisKeyLock, serverIdentity, resultSet));
                if (resultSet != null) {
                    if (resultSet.equals("OK")) {//无障碍获取锁成功
                        try {
                            Map<String, String> hash = new HashMap<String, String>();
                            hash.put("ip", serverIp);
                            hash.put("port", serverPort);
                            jedisCommands.hmset(groupRedisKey, hash);
                            logger.error("记入服务器IP：" + serverIp + ",Port：" + serverPort);
                        } catch (Exception ex) {
                            logger.error("记入服务器详细信息异常", ex);
                            while (true) {
                                try {
                                    jedisCommands.del(groupRedisKeyLock);
                                    logger.info("删除实例锁成功");
                                    break;
                                } catch (Exception e) {
                                    logger.error("删除实例锁异常", e);
                                }
                            }
                        }
                        logger.debug("good，成功拿到锁:" + serverIdentity);
                        return true;
                    } else {//不知道发生了啥情况，记录一下返回失败吧
                        logger.error("resultSet：" + resultSet);
                        return false;
                    }
                }
                String resultGet = jedisCommands.get(groupRedisKeyLock);//读取出来看看当前是不是自己存入的
                if (!serverIdentity.equals(resultGet)) {//如果不是自己的返回失败
                    logger.debug("当前占用锁的服务器实例：" + resultGet);
                    return false;
                }
                Long resultExpire = jedisCommands.expire(groupRedisKeyLock, expirePeriod);//对key执行延时操作
                logger.debug("resultExpire:" + resultExpire);
                resultGet = jedisCommands.get(groupRedisKeyLock);//读取出来看看还是不是自己存入的
                if (!serverIdentity.equals(resultGet)) {//如果不是自己的返回失败
                    logger.debug("瞬间被别的服务器实例抢去了锁，好伤心：" + resultGet);
                    return false;
                }
                logger.debug("对自己写入的锁进行了延时操作哦");
                return true;
            }
        } catch (Exception ex) {
            logger.error("抢占服务器实例锁发生异常", ex);
            return false;
        }
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public void setExpirePeriod(int expirePeriod) {
        this.expirePeriod = expirePeriod;
    }

    public void setHeartBeatCount(int heartBeatCount) {
        if (heartBeatCount < 2) {
            heartBeatCount = 2;
        }
        this.heartBeatCount = heartBeatCount;
    }

}