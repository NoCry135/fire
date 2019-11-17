package com.ca.fire.test.thread.passEx;

public class TestEx2 {

    public static void main(String[] args) {
        MyThreadGroup myThreadGroup = new MyThreadGroup("我的线程组!");

        MyThread myThread = new MyThread(myThreadGroup,"我的线程");
        //对象 存在只打印对象异常处理,再注释掉会打印静态异常和现场组异常,注释掉静态异常,只打印线程组异常
//        myThread.setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandler());

        //类
//        MyThread.setDefaultUncaughtExceptionHandler(new StateUNcaughtExceptionhandler());

        myThread.start();
    }
}
