package com.ca.fire.test.concurrency.atomic;

import org.junit.Test;

import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestVolatile {
    private   int num = 0;
    private static  AtomicInteger num1 = new AtomicInteger(0);

    @Test
    public void test01(){
        CountDownLatch countDownLatch = new CountDownLatch(20);

        for (int i =0 ;i< 20;i++) {

                new Thread(){
                    @Override
                    public void run() {
                        for (int j = 0; j < 10000; j++) {
                        add();
                            add1();
                        }
                        countDownLatch.countDown();
//                    add1();
                    }

                }.start();



        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//    while (Thread.activeCount() >2){
//            Thread.yield();
//    }
        System.out.println(num);
        System.out.println(num1.get());
    }

    @Test
    public void test02(){

        new Thread(){
            @Override
            public void run() {
                System.out.println("excute sub thread start");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateNum();
                System.out.println("excute sub thread end num=" + num);
            }
        }.start();
        while (num ==0){

        }
        System.out.println("main end");
    }

    private void updateNum(){
        num = 100;
    }

    private synchronized void  add(){
        num++;
    }


    private  void  add1(){
        num1.getAndIncrement();
    }


    static class Mydata {
        int value =0;
        AtomicInteger atomicInteger = new AtomicInteger();

        public void add(){
            value++;
        }
        public void addAtomic(){
            atomicInteger.getAndIncrement();
        }
    }

//    public static void main(String[] args) {
//        Mydata mydata = new Mydata();
//        for (int i = 0; i < 20; i++) {
//            new Thread(){
//                @Override
//                public void run() {
//                    for (int j = 0; j < 1000; j++) {
//                        mydata.add();
//                    }
//                }
//            }.start();
//        }
//        while (Thread.activeCount() >2){
//            Thread.yield();
//        }
//        System.out.println("mydata value = " + mydata.value);
//    }
}
