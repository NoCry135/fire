package com.ca.fire.test.concurrency.fell;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Test01 {
    private static int threadTotal = 1;
    private static int clientTotal = 5000;

    private static long count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exc = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        final  Semaphore semaphore = new Semaphore(threadTotal);
        for (int index = 0; index < clientTotal; index++) {

            exc.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            countDownLatch.countDown();
        }
        countDownLatch.await();
        exc.shutdown();
        System.out.println("count：" + count);
    }

    private synchronized static void add() {
        count++;
    }
}
