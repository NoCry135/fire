package com.ca.fire.test.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic {

    @Test
    public void testInteger() {

        AtomicInteger atomicInteger = new AtomicInteger(1);
        int andIncrement = atomicInteger.getAndIncrement();
        System.out.println(andIncrement);
        System.out.println(atomicInteger);
        System.out.println(atomicInteger.get() == 2);

    }
}
