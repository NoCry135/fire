package com.ca.fire.test.thread.exprint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintTest {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    public int flag = 1;


    public void print5() {
        lock.lock();
        try {
            while (flag != 1) {
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 5; i++) {
                System.out.println("当前线程:" + Thread.currentThread().getName() + ",=>" + i);
            }
            flag = 2;
            condition2.signal();
        } finally {
            lock.unlock();
        }


    }

    public void print10() {
        lock.lock();
        try {
            while (flag != 2) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 10; i++) {
                System.out.println("当前线程:" + Thread.currentThread().getName() + ",=>" + i);
            }
            flag = 3;
            condition3.signal();
        } finally {
            lock.unlock();
        }


    }

    public void print15() {
        lock.lock();
        try {
            while (flag != 3) {
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 15; i++) {
                System.out.println("当前线程:" + Thread.currentThread().getName() + ",=>" + i);
            }
            flag = 1;
            condition1.signal();
        } finally {
            lock.unlock();
        }


    }

}
