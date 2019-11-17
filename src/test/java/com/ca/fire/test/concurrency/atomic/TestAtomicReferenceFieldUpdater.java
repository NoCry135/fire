package com.ca.fire.test.concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class TestAtomicReferenceFieldUpdater {
    private volatile int count = 100;

    private static AtomicIntegerFieldUpdater<TestAtomicReferenceFieldUpdater> updater =
            AtomicIntegerFieldUpdater.newUpdater(TestAtomicReferenceFieldUpdater.class, "count");

    public static void main(String[] args) {
        TestAtomicReferenceFieldUpdater fieldUpdater = new TestAtomicReferenceFieldUpdater();
        if(updater.compareAndSet(fieldUpdater,100, 120)){
            System.out.println("update 1");
        }
        if(updater.compareAndSet(fieldUpdater,100, 120)){
            System.out.println("update 2");
        }else{
            System.out.println("update 3");

        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
