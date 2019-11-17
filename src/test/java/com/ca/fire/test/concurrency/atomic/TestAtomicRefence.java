package com.ca.fire.test.concurrency.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class TestAtomicRefence {

    private static AtomicReference<Integer> reference = new  AtomicReference<Integer>(0);

    public static void main(String[] args) {
        reference.compareAndSet(0,2);
        reference.compareAndSet(1,2);
        reference.compareAndSet(2,4);
        reference.compareAndSet(4,5);
        System.out.println(reference.get());
    }
}
