package com.ca.fire.test.threadlocal;

import java.util.concurrent.CountDownLatch;

public class ThreadLocalDemo {

    public static void main(String[] args) {
        int thread = 3;
        CountDownLatch countDownLatch = new CountDownLatch(thread);
        InnerClass innerClass = new InnerClass();
        for (int i = 0; i < thread; i++) {
            new Thread(() -> {
                for (int j = 0; j < 4; j++) {
                    innerClass.add(String.valueOf(j));
                    innerClass.print();
                }
            }, "Thread-" + i).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class InnerClass {


        public void add(String string) {
            StringBuilder stringBuilder = Counter.counter.get();
            Counter.counter.set(stringBuilder.append(string));
        }

        public void print() {
            System.out.printf("Thread name:%s , ThreadLocal hashcode: %s , Instance hashCode : %s , value: %s\n",
                    Thread.currentThread().getName(), Counter.counter.hashCode(), Counter.counter.get().hashCode(), Counter.counter.get().toString());

        }

        public void set(String string) {
            Counter.counter.set(new StringBuilder(string));
            System.out.printf("Set Thread name : %s , ThreadLocal hashCode: %s, Instance hashCode: %s, Value : %s\n",
                    Thread.currentThread().getName(), Counter.counter.hashCode(), Counter.counter.get().hashCode(), Counter.counter.get().toString());
        }

    }


    private static class Counter {
        private static ThreadLocal<StringBuilder> counter = new ThreadLocal<StringBuilder>() {
            @Override
            protected StringBuilder initialValue() {
                return new StringBuilder();
            }
        };
    }
}
