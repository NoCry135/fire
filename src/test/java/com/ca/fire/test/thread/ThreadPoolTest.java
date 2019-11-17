package com.ca.fire.test.thread;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPoolTest {


    @Test
    public void testFixPool() {
        Random random = new Random();
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 20; i++) {
            int time = random.nextInt(1000);
            String name = "线程 " + i;
            ThreadTest threadTest = new ThreadTest(name, time);
            System.out.println("增加: " + name + " / " + time);
            pool.execute(threadTest);
        }
    }
    @Test
    public void testSinglePool() {
        Random random = new Random();
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++) {
            int time = random.nextInt(1000);
            String name = "线程 " + i;
            ThreadTest threadTest = new ThreadTest(name, time);
            System.out.println("增加: " + name + " / " + time);
            pool.execute(threadTest);
        }
    }
    @Test
    public void testCachePool() {
        Random random = new Random();
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            int time = random.nextInt(1000);
            String name = "线程 " + i;
            ThreadTest threadTest = new ThreadTest(name, time);
            System.out.println("增加: " + name + " / " + time);
            pool.execute(threadTest);
        }
    }
    @Test
    public void testScheduledPool() {
        Random random = new Random();
        int ncpus = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU数量: " + ncpus);

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 20; i++) {
            int time = random.nextInt(1000);
            String name = "线程 " + i;
            ThreadTest threadTest = new ThreadTest(name, time);
            System.out.println("增加: " + name + " / " + time);
            pool.execute(threadTest);
        }
    }

    class ThreadTest implements Runnable {
        private final String name;
        private final int delay;

        public ThreadTest(String name, int delay) {
            this.name = name;
            this.delay = delay;
        }


        @Override
        public void run() {
            System.out.print("hello!" + name);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
