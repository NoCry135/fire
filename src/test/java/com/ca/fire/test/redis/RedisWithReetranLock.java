package com.ca.fire.test.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020/5/3
 */
public class RedisWithReetranLock {
    private ThreadLocal<Map<String, Integer>> lockers = new ThreadLocal<Map<String, Integer>>();
    private Jedis jedis;

    public RedisWithReetranLock(Jedis jedis) {
        this.jedis = jedis;
    }

    private boolean _lock(String key) {
        SetParams params = new SetParams().ex(5).nx();
        return jedis.set(key, "", params) != null;
    }

    private void _unlock(String key) {
        jedis.del(key);
    }

    private Map<String, Integer> currentLockers() {
        Map<String, Integer> refs = lockers.get();
        if (refs != null) {
            return refs;
        }
        lockers.set(new HashMap<>());
        return lockers.get();
    }

    public boolean lock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer reCnt = refs.get(key);
        if (reCnt != null) {
            refs.put(key, reCnt + 1);
            return true;
        }
        boolean ok = this._lock(key);
        if (!ok) {
            return false;
        }
        refs.put(key, 1);
        return true;
    }

    public boolean unlock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer reCnt = refs.get(key);
        if (reCnt == null) {
            return false;
        }
        reCnt -= 1;
        if (reCnt > 0) {
            refs.put(key, reCnt);
        } else {
            refs.remove(key);
            _unlock(key);
        }
        return true;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        RedisWithReetranLock lock = new RedisWithReetranLock(jedis);
        System.out.println(lock.lock("codehole"));
        System.out.println(lock.lock("codehole"));
        System.out.println(lock.unlock("codehole"));
        System.out.println(lock.unlock("codehole"));
    }
}
