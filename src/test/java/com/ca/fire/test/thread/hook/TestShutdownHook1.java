package com.ca.fire.test.thread.hook;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestShutdownHook1 {
    private static Timer timer = new Timer("job-time");

    private static AtomicInteger count = new AtomicInteger(0);


    static class CleanWorkThread extends Thread {
        @Override
        public void run() {
            System.out.println("clean some work ...");
            timer.cancel();

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        //将钩子线程添加到运行时环境中
        Runtime.getRuntime().addShutdownHook(new CleanWorkThread());
        System.out.println("main class start...");

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count.getAndDecrement();
                System.out.println("doing job " + count);
                if (count.get() == 10) {
                    System.out.println("线程退出!");
                    System.exit(0);
                }
            }
        }, 0, 2 * 1000);

    }
}
