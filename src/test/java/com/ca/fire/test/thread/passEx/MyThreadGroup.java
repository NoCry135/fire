package com.ca.fire.test.thread.passEx;

public class MyThreadGroup extends java.lang.ThreadGroup {
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        super.uncaughtException(t, e);
        System.out.println("线程组的异常处理!!!");
        e.printStackTrace();
    }
}
