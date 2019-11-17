package com.ca.fire.test.thread;

public class TestThread01 {
    public static void main(String[] args) {
//        MyThread myThread = new MyThread();
//        myThread.start();
        System.out.println("main...");

//        Runnable myThreadRunnable = new MyThreadRunnable();
//        Thread domain = new Thread(myThreadRunnable);
//        domain.start();

//        Thread myThred03 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("my anon domain...");
//            }
//        });
//        myThred03.start();

        Thread thread = new Thread(() -> {
            System.out.println("lambda domain ...");
        });
        thread.start();
    }
}

/**
 * 继承thread类
 */
class MyThread extends Thread {
    @Override
    public void run() {
        System.err.println("MyThread...");
    }
}

class MyThreadRunnable implements Runnable{
    @Override
    public void run() {
        System.err.println("MyRunnable...");
    }
}

