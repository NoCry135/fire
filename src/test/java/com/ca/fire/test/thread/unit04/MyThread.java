package com.ca.fire.test.thread.unit04;

public class MyThread extends Thread {

    private int count = 5;

    @Override
    public void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + "count:" + count);
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread a = new Thread(myThread, "A");
        Thread b = new Thread(myThread, "B");
        Thread c = new Thread(myThread, "C");
        Thread d = new Thread(myThread, "D");

        a.start();
        b.start();
        c.start();
        d.start();
    }
}
