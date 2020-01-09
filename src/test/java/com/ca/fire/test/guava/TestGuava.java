package com.ca.fire.test.guava;


import com.google.common.util.concurrent.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class TestGuava {

    private static int num = 0;

    private static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) {
        //定义Callback
        FutureCallback<Integer> integerFutureCallback = new FutureCallback<Integer>() {
            @Override/*线程池执行结果对象 以下是integer*/
            public void onSuccess(Integer integer) {
                System.out.println("数值为:" + integer);
                num += integer;
                System.out.println("最终值为"+num);
            }
            @Override/*异常*/
            public void onFailure(Throwable throwable) {
                System.out.println(throwable.toString());
            }
        };
        //ExecutorService 线程池 转换为 ListeningExecutorService
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        //创建 listenFutureCallback
        ListenableFuture<Integer> integerListenableFuture = null;

        long st= System.currentTimeMillis();

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            integerListenableFuture = listeningExecutorService.submit(() -> {
                return Integer.valueOf(1 + finalI);
            });
            Futures.addCallback(integerListenableFuture,integerFutureCallback);
        }

        System.out.println("开始关闭线程池……");

        listeningExecutorService.shutdownNow();

        System.out.println("线程池关闭中……");

        System.out.println("线程池状态:" + listeningExecutorService.isShutdown() + listeningExecutorService.isTerminated());

        try {
            System.out.println("最终数据" + integerListenableFuture.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("耗时:"+(System.currentTimeMillis()-st));
    }
}
