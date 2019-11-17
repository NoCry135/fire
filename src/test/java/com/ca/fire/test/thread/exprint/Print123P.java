package com.ca.fire.test.thread.exprint;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Print123P {
    private int flag = 1;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print1() {
        lock.lock();
        try {
            while (flag != 1) {
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程" + Thread.currentThread().getName() + "打印" + 1);
            flag = 2;
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print2() {
        lock.lock();
        try {
            while (flag != 2) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程" + Thread.currentThread().getName() + "打印" + 2);
            System.out.println("线程" + Thread.currentThread().getName() + "打印" + 2);
            flag = 3;
            condition3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print3() {
        lock.lock();
        try {
            while (flag != 3) {
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程" + Thread.currentThread().getName() + "打印" + 3);
            System.out.println("线程" + Thread.currentThread().getName() + "打印" + 3);
            System.out.println("线程" + Thread.currentThread().getName() + "打印" + 3);
            flag = 1;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Print123P print123 = new Print123P();
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
