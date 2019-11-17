package com.ca.fire.test.thread.exprint;

import java.util.concurrent.TimeUnit;

public class Print123 {
    private int flag = 1;

    public synchronized void print1() {
        while (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程" + Thread.currentThread().getName() + "打印" + 1);
        flag = 2;
        this.notifyAll();
    }

    public synchronized void print2() {
        while (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程" + Thread.currentThread().getName() + "打印" + 2);
        System.out.println("线程" + Thread.currentThread().getName() + "打印" + 2);
        flag = 3;
        this.notifyAll();
    }

    public synchronized void print3() {
        while (flag != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程" + Thread.currentThread().getName() + "打印" + 3);
        System.out.println("线程" + Thread.currentThread().getName() + "打印" + 3);
        System.out.println("线程" + Thread.currentThread().getName() + "打印" + 3);
        flag = 1;
        this.notifyAll();
    }

    public static void main(String[] args) {
        Print123 print123 = new Print123();
        new Thread("线程1") {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    print123.print1();
                }

            }
        }.start();
        new Thread("线程2") {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    print123.print2();
                }

            }
        }.start();
        new Thread("线程3") {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    print123.print3();
                }

            }
        }.start();
    }
}
