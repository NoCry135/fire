package com.ca.fire.test.thread.unit02;

public class DeadThread implements Runnable {
    private String userName;

    private Object lock1 = new Object();

    private Object lock2 = new Object();

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {

        if ("a".equals(userName)) {
            synchronized (lock1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("username=" + userName);
                synchronized (lock2) {
                    System.out.println("lock1==>lock2 执行");
                }
            }
        }

        if ("b".equals(userName)) {
            synchronized (lock2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("username=" + userName);
                synchronized (lock1) {
                    System.out.println("lock2==>lock1 执行");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadThread deadThread = new DeadThread();
        deadThread.setUserName("a");
        Thread thread1 = new Thread(deadThread);
        thread1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deadThread.setUserName("b");
        Thread thread2 = new Thread(deadThread);
        thread2.start();
    }
}
