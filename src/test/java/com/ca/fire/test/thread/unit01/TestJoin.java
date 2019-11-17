package com.ca.fire.test.thread.unit01;

public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread wang = new Thread(new EatEgg(), "小王");
        Thread li = new Thread(new EatEgg(), "小李");
        wang.start();
        li.start();
        wang.join();

    }

}


class EatEgg implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("当前线程:" + Thread.currentThread().getName() + "吃了第" + (i + 1) + "个鸡蛋");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}