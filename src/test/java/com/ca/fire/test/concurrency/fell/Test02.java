package com.ca.fire.test.concurrency.fell;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
public class Test02 {

    private static Map<Integer, Integer> map = new HashMap<>();

    private static int threadTotal = 1;
    private static int clientTotal = 5000;

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);

        for (int index = 0; index < clientTotal; index++) {
            final int threadNum = index;
            service.execute(() -> {
                try {
                    semaphore.acquire();
                    func(threadNum);
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
        System.out.println("map.size:" + map.size());

    }

    private static void func(int threadNum) {
        map.put(threadNum, threadNum) ;
    }

}
