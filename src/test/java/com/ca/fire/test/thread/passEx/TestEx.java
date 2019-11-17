package com.ca.fire.test.thread.passEx;

public class TestEx {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        //对象
//        myThread.setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandler());

        //类
        MyThread.setDefaultUncaughtExceptionHandler(new StateUNcaughtExceptionhandler());

        myThread.start();
    }
}
