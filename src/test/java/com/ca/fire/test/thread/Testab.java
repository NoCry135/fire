package com.ca.fire.test.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created on 2020/3/17
 */
public class Testab {


    volatile int a = 1;
    volatile int b = 1;

    public void add() {
        System.out.println("add start");
        for (int i = 0; i < 10000; i++) {
            a++;
            b++;
        }
        System.out.println("add done");
    }

    public void compare() {
        System.out.println("compare start");
        for (int i = 0; i < 10000; i++) {
            //a始终等于b吗？
            if (a < b) {
                System.out.printf("a:%s,b:%s,%s", a, b, a > b);
                //最后的a>b应该始终是false吗？
            }
        }
        System.out.println("compare done");
    }

    @Test
    public  void main1() {
        Testab interesting = new Testab();
        new Thread(() -> interesting.add()).start();
        new Thread(() -> interesting.compare()).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//匿名类
        new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("hello1");
            }
        }).start();
//Lambda表达式
        new Thread(() -> System.out.println("hello2")).start();
    }
}
