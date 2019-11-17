package com.ca.fire.test.thread;

public class SynchronizedMethodThread extends Thread {

    private MyObject object;

    public SynchronizedMethodThread(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("A")) {
            object.methodA();
        } else {
            object.methodB();
        }
    }
}
