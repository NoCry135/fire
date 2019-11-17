package com.ca.fire.test.thread.local;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class MyMethod {


    private ApplicationContext context = null;

    @Before
    public void testBefore() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");

    }


    @Test
    public void test01() {
        MyData myData = (MyData) context.getBean("myData");

        myData.setData("我是Tom!");
        test11(myData);
        System.out.println("另外一个线程开始开启");
        MyData myData1 = context.getBean(MyData.class);
        myData1.setData("我是Jim");
        test22(myData1);
    }

    private void test11(MyData myData) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myData);
    }

    private void test22(MyData myData) {
        System.out.println(myData);
    }

    public static void main(String[] args) {
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    System.out.println("Uh-huh , the nice music");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        browseNews();
    }
    @Test
    public void test02() {
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    System.out.println("Uh-huh , the nice music");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        browseNews();
    }


    public static void browseNews() {
        while (true) {
            System.out.println("Uh-huh , the goods news");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
