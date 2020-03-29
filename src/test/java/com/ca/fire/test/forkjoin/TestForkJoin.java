package com.ca.fire.test.forkjoin;

import com.ca.fire.domain.bean.User;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Timer;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class TestForkJoin {
    private final static int ITEM_COUNT = 10;
    private final static int LOOP_COUNT = 10000000;
    private final static int THREAD_COUNT = 10;

    @Test
    public void test() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("forkJoinPool");
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(i -> {
                    String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                    freqs.computeIfAbsent(key, k -> new LongAdder()).increment();
                }
        ));
        forkJoinPool.shutdown();
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    }

    @Test
    public void test02() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("watch");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
        System.out.println(stopWatch.getTotalTimeMillis());

        stopWatch.start("watch1");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    @SneakyThrows
    @Test
    public void test01() {
//        ExecutorService executorService = Executors.newCachedThreadPool();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors + 1);

//        CountDownLatch countDownLatch = new CountDownLatch(100);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("watch");
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setAge(i);
            user.setUserName("user:" + i);
            compute(user);
//            executorService.execute(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                compute(user);
//                            } finally {
//                                countDownLatch.countDown();
//                            }
//                        }
//                    }
//
//            );

        }
//        countDownLatch.await();
        stopWatch.stop();
//        executorService.shutdown();
        System.out.println("================================================" + stopWatch.getLastTaskTimeMillis());
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    private void compute() {
//        System.out.println(user);
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void compute(User user) {
        System.out.println(Thread.currentThread().getName() + ":" + user);
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Test
    public void test03() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("watch");

        //CallerRunsPolicy 主线程去执行任务
        //AbortPolicy 拒绝并抛出异常
        //DiscardPolicy 不处理新任务之间丢弃，为啥不退出
        //DiscardOldestPolicy 丢弃队列最老的请求，为啥不退出
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 2,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(5),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(10);
        try {
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setAge(i);
                user.setUserName("user:" + i);
                threadPoolExecutor.execute(() -> {
                    try {

                        compute(user);
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            }
            System.out.println("await");
            countDownLatch.await();
            System.out.println("finish");
            stopWatch.stop();
            System.out.println("over" + stopWatch.prettyPrint());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }


    }

    private void sleep3S() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("线程执行完成:" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
