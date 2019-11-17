package com.ca.fire.test.thread.exception;

public class GroupExceptionThread extends Thread {

    private String num;

    public GroupExceptionThread(ThreadGroup group, String name, String num) {
        super(group, name);
        this.num = num;
    }

    @Override
    public void run() {
        int parseInt = Integer.parseInt(num);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            System.out.println("死循环中:" + Thread.currentThread().getName());
        }
    }
}

class TestGroupEx {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("线程组A");
        GroupExceptionThread[] threads = new GroupExceptionThread[10];
        new GroupExceptionThread(threadGroup, "线程出错", "a").start();

        for (int i = 0; i < 1; i++) {
            threads[i] = new GroupExceptionThread(threadGroup, "线程" + (i + 1), "1");
            threads[i].start();
        }
    }


}