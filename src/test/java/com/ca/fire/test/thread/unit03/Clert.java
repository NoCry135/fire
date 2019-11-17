package com.ca.fire.test.thread.unit03;

public class Clert {

    private int num;

    public synchronized void product() {
        if (num > 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "生成商品，当前库存：" + ++num);
//            notify();
            notifyAll();
        }
    }

    public synchronized void consume() {
        if (num > 0) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "消费商品，当前库存：" + --num);
            //必须在同步方法或同步代码块中使用
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
