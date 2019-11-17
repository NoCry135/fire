package com.ca.fire.test.redis;


import com.ca.fire.cache.MyJedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/spring-config.xml"})
public class testCache {

    @Resource
    private MyJedisClient myJedisClinet;

    @Test
    public void test01(){
        System.out.println(myJedisClinet);
    }
}
