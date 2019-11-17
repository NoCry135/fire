package com.ca.fire.test.thread.unit03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class TestFurtureTask {

    private static List<Integer> list = Arrays.asList(10, 20, 30, 40, 50);

    public static void main(String[] args) {
        int result = 0;
        long start = System.currentTimeMillis();
        for (Integer arg : list) {
            result += sum(arg);
        }
        long end = System.currentTimeMillis();
        System.out.println("result:" + result + ",spend time:" + (end - start) + "ms");

        long l1 = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

//        List<Future<Integer>> resultList = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();

        for (Integer arg : list) {
            Future<Integer> integerFuture = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return sum(arg);
                }
            });
//            resultList.add(integerFuture);
            try {
                resultList.add(integerFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        int sum = 0;
        for (Integer integer : resultList) {
            sum += integer;
        }

//        for (Future<Integer> integerFuture : resultList) {
//            try {
//                Integer integer = integerFuture.get();
//                sum += integer;
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//
//        }
        long l2 = System.currentTimeMillis();

        System.out.println("sum : " + sum + ",sum spend time:" + (l2 - l1));
        executorService.shutdown();
    }


    public static int sum(int num) {
        int result = 0;
        for (int i = 1; i < num; i++) {

            result += i;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
