package com.ca.fire.test.thread.exception;

public class GroupExceptionThreadEX extends Thread {

    private String num;


    public GroupExceptionThreadEX(ThreadGroup group, String name, String num) {
        super(group, name);
        this.num = num;
    }

    @Override
    public void run() {
        int parseInt = Integer.parseInt(num);
        while (this.isInterrupted() == false) {
            System.out.println("死循环中:" + Thread.currentThread().getName());

        }
    }
}

class GroupEx extends ThreadGroup {

    public GroupEx(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        super.uncaughtException(t, e);
        this.interrupt();
    }
}

class TestGroupEx1 {
    public static void main(String[] args) {
        GroupEx threadGroup = new GroupEx("线程组A");
        GroupExceptionThreadEX[] threads = new GroupExceptionThreadEX[10];
        new GroupExceptionThreadEX(threadGroup, "线程出错", "a").start();

        for (int i = 0; i < 1; i++) {
            threads[i] = new GroupExceptionThreadEX(threadGroup, "线程" + (i + 1), "1");
            threads[i].start();
        }
    }
}