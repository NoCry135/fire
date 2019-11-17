package com.ca.fire.test.thread.passEx;

public class ObjectUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("对象异常处理!!!");
        e.printStackTrace();
    }
}
