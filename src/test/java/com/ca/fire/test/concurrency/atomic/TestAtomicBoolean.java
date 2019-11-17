package com.ca.fire.test.concurrency.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestAtomicBoolean {
    private static AtomicBoolean flag = new AtomicBoolean(false);

    private static int thread = 200;

    private static int client = 5000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(client);
        CountDownLatch countDownLatch = new CountDownLatch(thread);
        for (int i = 0; i < client; i++) {
            executorService.execute( () ->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                    countDownLatch.countDown();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

        }


        countDownLatch.await();
        executorService.shutdown();
    }

    private static void test() {
        if (flag.compareAndSet(false, true)) {
            System.out.println("1111111111111");
        }

    }

}
