package com.ca.fire.test.thread.exception;

public class ExceptionThread implements Runnable {

    @Override
    public void run() {
        System.out.println("线程执行异常 1/0=" + 1 / 0);
    }
}

class TestEx {

    public static void main(String[] args) {
        ExceptionThread exceptionThread = new ExceptionThread();

        Thread thread = new Thread(exceptionThread);
        thread.start();

        Thread thread1 = new Thread(exceptionThread);
        thread1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程:" + t.getName() + "出现异常:" + e.getMessage());
                e.printStackTrace();
            }
        });
        thread1.start();
    }
}

class TestExAll {

    public static void main(String[] args) {
        ExceptionThread exceptionThread = new ExceptionThread();
        Thread thread = new Thread(exceptionThread);
        thread.start();

        Thread thread1 = new Thread(exceptionThread);
        thread1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程:" + t.getName() + "出现异常:" + e.getMessage());
                e.printStackTrace();
            }
        });
        thread1.start();
    }
}
