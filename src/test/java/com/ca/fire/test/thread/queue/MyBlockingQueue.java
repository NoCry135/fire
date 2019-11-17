package com.ca.fire.test.thread.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyBlockingQueue<E> {
    private final List list;
    private final int limit;

    public MyBlockingQueue(int limit) {
        this.list = new ArrayList<E>();
        this.limit = limit;
    }

    public synchronized void put(E e) {
        while (list.size() == limit) {

            try {
                wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("list.size:" + list.size());
        System.out.println("list.add");

        list.add(e);
        notifyAll();
    }

    public synchronized E take() {
        while (list.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("list.size:" + list.size());
        System.out.println("list.remove");
        E remove = (E) list.remove(0);
        System.out.println("remove.value:" + remove.toString());
        notifyAll();
        return remove;
    }

    public static void main(String[] args) {
        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue(10);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new TestRunnable(myBlockingQueue));

        }
    }

    static class TestRunnable implements Runnable {
        private final MyBlockingQueue<Integer> myBlockingQueue;

        public TestRunnable(MyBlockingQueue<Integer> myBlockingQueue) {
            this.myBlockingQueue = myBlockingQueue;
        }

        @Override
        public void run() {
            Random random = new Random();
            int nextInt = random.nextInt(100);
            if (nextInt < 30) {
                myBlockingQueue.put(nextInt);
            } else {
                myBlockingQueue.take();

            }
        }
    }
}
