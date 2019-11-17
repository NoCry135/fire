package com.ca.fire.test.java8.lambda;

public class TestLambda01 {
    public static void main(String[] args) {
        //传统的方式,创建匿名内部类启动线程
        new Thread(new MyThread() {
        }).start();
        System.out.println("main...");

        //使用lambda表达式
        new Thread(() -> {
            System.out.println("myLambda Thread...");
        }).start();
    }

}

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println("MyThread...");
    }
}