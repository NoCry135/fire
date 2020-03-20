package com.ca.fire.test.concurrency;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created on 2020/3/10
 */
public class TestStopwatch {

    @Test
    public void test01() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("normaluse");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.getLastTaskTimeMillis());
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println(stopWatch.getTotalTimeSeconds());
    }

    @Test
    public void test02() {
        Map<Object, Object> hashMap = new ConcurrentHashMap<>();
        //相同的key，只能put一次，后边的key存在。不会put成功，返回第一次的值
        System.out.println(hashMap.putIfAbsent(1, 1)); //null
        System.out.println(hashMap.putIfAbsent(1, 2)); //1
        System.out.println(hashMap.putIfAbsent(1, 2)); //2
        System.out.println(hashMap.putIfAbsent(1, 3)); //2

        System.out.println(hashMap.putIfAbsent(2, 3));
        System.out.println(hashMap.putIfAbsent(2, 3));
        System.out.println(hashMap.putIfAbsent(2, 4));
    }

    @Test
    public void test03() {
        Map<Object, LongAdder> hashMap = new ConcurrentHashMap<>();
        hashMap.computeIfAbsent(1, k -> new LongAdder());
        hashMap.computeIfAbsent(1, k -> new LongAdder()).increment();
        System.out.println(hashMap);  //1,1
        hashMap.computeIfAbsent(1, k -> new LongAdder()).increment();
        System.out.println(hashMap);    //1,2
        hashMap.computeIfAbsent(2, k -> new LongAdder()).increment();
        System.out.println(hashMap);//2,1
        hashMap.computeIfAbsent(3, k -> new LongAdder()).increment();
        System.out.println(hashMap);//3.0
        hashMap.computeIfAbsent(2, k -> new LongAdder()).increment();
        System.out.println(hashMap);//2,2
        hashMap.computeIfAbsent(3, k -> new LongAdder()).increment();
        System.out.println(hashMap);//3.3
        hashMap.computeIfAbsent(2, k -> new LongAdder()).increment();
        System.out.println(hashMap);//2,3

//        System.out.println(hashMap.computeIfAbsent(2, 4));
    }
    @Test
    public void test04() {
        Map<Object, LongAdder> hashMap = new ConcurrentHashMap<>();
//        computeIfPresent

//        System.out.println(hashMap.computeIfAbsent(2, 4));
    }
    @Test
    public void testMap() {
        //添加新值返回旧的value
        Map<String, String> map = new HashMap<>();
        String put = map.put("a", "A");
        System.out.println(put); //null
        String put1 = map.put("b", "B");//null
        System.out.println(put1);
        String v = map.put("b", "v"); // 输出 B
        System.out.println(v);
        String v1 = map.put("c", "v");
        System.out.println(v1); // 输出：NULL
    }

}
