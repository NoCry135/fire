package com.ca.fire.test.guava.current;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2020/1/9
 */
public class ListenableFutureDemo {

    public static void main(String[] args) {
//        testRateLimiter();
//        testListenableFuture();
        testListenableFuture1();
    }

    /**
     * RateLimiter类似于JDK的信号量Semphore，他用来限制对资源并发访问的线程数
     */
    public static void testRateLimiter() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));


        RateLimiter limiter = RateLimiter.create(5.0); // 每秒不超过4个任务被提交


        for (int i = 0; i < 10; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞


            final ListenableFuture<Integer> listenableFuture = executorService.submit(new Task("is " + i, i));

            Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
                @Override
                public void onSuccess(Integer result) {
                    System.out.println("getlimiter result with callback " + result);
                }


                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    public static void testListenableFuture() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        for (int i = 0; i < 10; i++) {
            final ListenableFuture<Integer> listenableFuture = executorService.submit(new Task("testListenableFuture", i));

//同步获取调用结果
            try {
                System.out.println(listenableFuture.get());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
//第一种方式
            listenableFuture.addListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("get listenable future's result " + listenableFuture.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }, executorService);
//第二种方式
            Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
                @Override
                public void onSuccess(Integer result) {
                    System.out.println("get listenable future's result with callback " + result);
                }


                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }

    }

    public static void testListenableFuture1() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        List<ListenableFuture<Integer>> monitor = Lists.newArrayList();
        long st = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            final ListenableFuture<Integer> listenableFuture = executorService.submit(new Task("testListenableFuture", i));
            monitor.add(listenableFuture);

        }
        ListenableFuture<List<Integer>> listListenableFuture = Futures.allAsList(monitor);
        try {
            listListenableFuture.addListener(new Runnable() {
                @Override
                public void run() {
//                    try {
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        System.out.println("116 exception");
//                        e.printStackTrace();
//                    }
                }
            }, executorService);
            System.out.println("get listenable future's result " + listListenableFuture.get());

        } catch (Exception e) {
            System.out.println("121 exception");
            e.printStackTrace();
        } finally {
            long en = System.currentTimeMillis();
            System.out.println("spend:" + (en - st));
            executorService.shutdown();
        }

    }

    public static void testListenableFuture2() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        List<ListenableFuture<Integer>> monitor = Lists.newArrayList();
        long st = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            final ListenableFuture<Integer> listenableFuture = executorService.submit(new Task("testListenableFuture", i));
            monitor.add(listenableFuture);

        }
        ListenableFuture<List<Integer>> listListenableFuture = Futures.allAsList(monitor);
        try {
            Futures.addCallback(listListenableFuture, new FutureCallback<Object>() {
                @Override
                public void onSuccess(Object result) {
                    System.out.println("异步执行成功");
                }

                @Override
                public void onFailure(Throwable t) {
                    throw new RuntimeException(t.getMessage());
                }
            }, MoreExecutors.directExecutor());
            listListenableFuture.get();
        } catch (Exception e) {
            System.out.println("121 exception");
            e.printStackTrace();
        } finally {
            long en = System.currentTimeMillis();
            System.out.println("spend:" + (en - st));
            executorService.shutdown();
        }

    }
}


class Task implements Callable<Integer> {
    String str;
    int i;

    public Task(String str, int i) {
        this.str = str;
        this.i = i;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("call execute.." + str + ", i= " + i);
        TimeUnit.SECONDS.sleep(1);
        return i;
    }
}
