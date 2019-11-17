package com.ca.fire.test.collections;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentMap {

    @Test
    public void test01() {

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        System.out.println(concurrentHashMap.putIfAbsent("1", "2"));
        System.out.println(concurrentHashMap.putIfAbsent("1", "2"));
        System.out.println(concurrentHashMap.putIfAbsent("1", "3"));
        System.out.println(concurrentHashMap.putIfAbsent("2", "3"));
        System.out.println(concurrentHashMap);
    }
}
