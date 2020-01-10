package com.ca.fire.test.concurrency;

/**
 * Created on 2020/1/3
 */

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample3 {
    //回调方法
    private static CyclicBarrier barrier = new CyclicBarrier(5, () -> {
        System.out.println("callback is running");
    });

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        System.out.println(threadNum + "{} is ready");
        barrier.await();
        System.out.println("{} continue" + threadNum);

    }
}