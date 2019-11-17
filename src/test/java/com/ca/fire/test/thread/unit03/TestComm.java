package com.ca.fire.test.thread.unit03;


public class TestComm {

    public static void main(String[] args) {
        ThreadPrint threadPrint = new ThreadPrint();
        Thread a = new Thread(threadPrint, "A");
        Thread b = new Thread(threadPrint, "B");
        a.start();
        b.start();
    }
}

/**
 * 两个线程交替打印
 **/

class ThreadPrint implements Runnable {
    private Integer num = 100;

    @Override
    public void run() {

        while (true) {
            synchronized (this) {
                notify();
                if (num > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("当前线程[" + Thread.currentThread().getName() + "] num=" + --num);
                } else {
                    System.out.println("当前线程[" + Thread.currentThread().getName() + "] 打印结束 num" + --num);
                    break;
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}