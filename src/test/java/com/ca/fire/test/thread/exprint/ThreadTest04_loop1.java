package com.ca.fire.test.thread.exprint;

class Print {

    public int flg = 1;

    public synchronized void print5(int loop) {
        while (flg != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "第" + loop + "轮" + "打印 ：" + i);
        }
        flg = 2;
        this.notifyAll();
    }

    public synchronized void print10(int loop) {
        while (flg != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "第" + loop + "轮" + "打印 ：" + i);
        }
        flg = 3;
        this.notifyAll();
    }

    public synchronized void print15(int loop) {
        while (flg != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 15; i++) {
            System.out.println(Thread.currentThread().getName() + "第" + loop + "轮" + "打印 ：" + i);
        }
        flg = 1;
        this.notifyAll();
    }
}

public class ThreadTest04_loop1 {

    public static void main(String[] args) throws Exception {

        Print print = new Print();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    print.print5(i);
                }
            }
        }, "第一个线程").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    print.print10(i);
                }
            }
        }, "第二个线程").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    print.print15(i);
                }
            }
        }, "第三个线程").start();

    }
}
