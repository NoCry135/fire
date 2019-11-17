package com.ca.fire.test.spring.init;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class TestSpringBeanInit {
    private ClassPathXmlApplicationContext  act;

    @Before
    public void before(){
        act = new ClassPathXmlApplicationContext("spring-bean.xml");
    }

    @Test
    public void testInit() throws InterruptedException {
//        Object initBean = act.getBean("initBean");
        InitBean bean = act.getBean(InitBean.class);
        System.out.println(bean.toString());
        TimeUnit.SECONDS.sleep(2);
        act.close();
    }


}
