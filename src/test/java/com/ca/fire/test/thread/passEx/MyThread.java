package com.ca.fire.test.thread.passEx;

public class MyThread extends Thread {
    private String num = "a";

    public MyThread() {
    }

    public MyThread(MyThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        int parseInt = Integer.parseInt(num);
        System.out.println("在线程中打印: " + (parseInt + 1));
    }
}
