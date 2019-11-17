package com.ca.fire.test.thread.exprint;

public class Print5Test {
    private int flag = 1;

    public synchronized void print2() {
        while (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("线程:" + Thread.currentThread().getName() + "打印" + i);
        }
        flag = 2;
        this.notifyAll();
    }
    public synchronized void print4() {
        while (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 4; i++) {
            System.out.println("线程:" + Thread.currentThread().getName() + "打印" + i);
        }
        flag = 3;
        this.notifyAll();
    }

    public synchronized void print6() {
        while (flag != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 6; i++) {
            System.out.println("线程:" + Thread.currentThread().getName() + "打印" + i);
        }
        flag = 1;
        this.notifyAll();
    }

    public static void main(String[] args) {
        Print5Test print5Test = new Print5Test();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    print5Test.print2();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    print5Test.print4();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    print5Test.print6();
                }
            }
        }.start();
    }
}
