package com.ca.fire.test.thread.unit02;

public class TestSynchronize {
    public static void main(String[] args) {
        SellTicket sellTicket = new SellTicket();
        //出现超卖
        new Thread(sellTicket, "A").start();
        new Thread(sellTicket, "B").start();
        new Thread(sellTicket, "C").start();
    }
}

class SellTicket implements Runnable {
    private int ticket = 100;

    @Override
    public void run() {
        while (true) {

            synchronized (this) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("售票窗口" + Thread.currentThread().getName() + "卖了一张票 ,还剩下" + --ticket + "张");
                } else {
                    System.out.println("售票窗口" + Thread.currentThread().getName() + "票卖完了");
                    break;
                }
            }
        }
    }
}