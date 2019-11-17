package com.ca.fire.test.concurrency.atomic;

import java.util.concurrent.atomic.AtomicInteger;

 class Mydata {
    int value =0;
    AtomicInteger atomicInteger = new AtomicInteger();

    public void add(){
        value++;
    }
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}
public class TestVolatile1 {

    public static void main(String[] args) {
        Mydata mydata = new Mydata();
        for (int i = 0; i < 20; i++) {
            new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        mydata.add();
                    }
                }
            }.start();
        }
        while (Thread.activeCount() >2){
            Thread.yield();
        }
        System.out.println("mydata value = " + mydata.value);
    }
}
