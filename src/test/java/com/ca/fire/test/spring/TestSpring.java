package com.ca.fire.test.spring;

import com.ca.fire.test.thread.local.MyData;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestSpring {
    ApplicationContext act = new ClassPathXmlApplicationContext("spring-config.xml");

    @Test
    public void test01() {
        MyData bean = act.getBean(MyData.class);
        Map<Object, Object> objectObjectMap = Collections.unmodifiableMap(new HashMap<>());
    }

    @Test
    public void test02() {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("11", 1);
        objectObjectHashMap.put("22", 2);
        Map<Object, Object> objectObjectMap = Collections.unmodifiableMap(objectObjectHashMap);
        Set<Map.Entry<Object, Object>> entries = objectObjectMap.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key= " + key + "; value= " + value);
        }
        objectObjectHashMap.put("33", 3);
        entries = objectObjectMap.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key= " + key + "; value= " + value);
        }
    }

}
