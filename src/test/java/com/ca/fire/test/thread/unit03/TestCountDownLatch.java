package com.ca.fire.test.thread.unit03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    private static List<Integer> list = Arrays.asList(10, 20, 30, 40, 50);

    public static void main(String[] args) {
        List<Integer> resultList = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(list.size());
        long start = System.currentTimeMillis();
        for (Integer arg : list) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    resultList.add(sum(arg));
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = 0;
        for (Integer integer : resultList) {
            result += integer;
        }

        long end = System.currentTimeMillis();
        System.out.println("result:" + result + ",spend time:" + (end - start) + "ms");


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
