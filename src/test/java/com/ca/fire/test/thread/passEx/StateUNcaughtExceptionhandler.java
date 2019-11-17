package com.ca.fire.test.thread.passEx;

import java.lang.Thread.UncaughtExceptionHandler;

public class StateUNcaughtExceptionhandler implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("静态异常处理!!!");
        e.printStackTrace();
    }
}
