package com.ca.fire.test.thread.exprint;

public class ThreadTestloop {

    public static void main(String[] args) {
        Thread thread01 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("线程:" + Thread.currentThread().getName() + "打印" + i);
                }
            }
        });
        thread01.start();
        Thread thread02= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread01.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10;i++) {
                    System.out.println("线程:" + Thread.currentThread().getName() + "打印" + i);
                }
            }
        });

        thread02.start();
        Thread thread03= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread02.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 15;i++) {
                    System.out.println("线程:" + Thread.currentThread().getName() + "打印" + i);
                }
            }
        });
        thread03.start();

    }
}
