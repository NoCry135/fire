package com.ca.fire.test.collections;

import com.ca.fire.domain.bean.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.junit.Test;
import org.omg.CORBA_2_3.portable.InputStream;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class TestMap {


    private static Map<String, String> cacheMap = new HashMap<>();

    private static Map<String, String> concurrentAcheMap = new ConcurrentHashMap<>();

    @Test
    public void testcacheMap() {
//        CollectionUtils.isNotEmpty();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10, 10, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>()
        );
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadPoolExecutor.execute(
                    new Thread() {

                        @Override
                        public void run() {
//                            Random random = new Random();
//                            String key = Thread.currentThread().getId() + "_" + random.nextInt(10);
//                            String value = key + ":" + System.currentTimeMillis();
//                            cacheMap.put(key, value);
                            visist();
                        }
                    }

            );

        }


    }

    private void visist() {
        Random random = new Random();
        String key = Thread.currentThread().getId() + "_" + random.nextInt(10);
        String value = getValue(key);
        System.out.println("key:" + key + "; value:" + value);
    }


    public String getValue(String key) {
        if (cacheMap.containsKey(key)) {
            return cacheMap.get(key);
        }
        String value = key + ":" + System.currentTimeMillis();
        cacheMap.put(key, value);
        return value;

    }


    public static void main(String[] args) {
        Map<String, Object> result = new CaseInsensitiveMap();
        result.put("sss", "ok");
        result.put("baB", "ok");
        result.put("BAc", "ok");
        result.put("aaa", "ok");
        result.put("AAA", "AAA");
        System.out.println(result.get("SSS"));
        System.out.println(result.get("BAb"));
        System.out.println(result.get("baC"));
        System.out.println(result.get("AAA"));
    }


    @Test
    public void test01() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        Object put = hashMap.put(null, "111");
        System.out.println(hashMap.size());
    }

    @Test
    public void test02() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(1, "111");
        hashMap.put(2, "222");
        hashMap.put(3, "333");
        Collection<Object> values = hashMap.values();
        System.out.println(hashMap.size());
    }

    @Test
    public void test03() {
        HashMap<Object, User> hashMap = new HashMap<>();
        User user = new User();
        hashMap.put(1, user);
        user.setUserName("222");
        System.out.println(hashMap);
        User obj = hashMap.get("2");
        if (obj == null) {
            obj = new User();
        }
        obj.setUserName("333");

        System.out.println(hashMap);
        HashMap<Object, List<User>> map = new HashMap<>();

        List<User> users = new ArrayList<>();
        map.put("users", users);
        users.add(user);
        System.out.println(map);
    }

}
