package com.ca.fire.test.thread;

import org.junit.Test;

public class SynchronizedMethodThreadTest {

    @Test
    public void test01() throws InterruptedException {
        MyObject object = new MyObject();
        SynchronizedMethodThread a = new SynchronizedMethodThread(object);
        a.setName("A");
        SynchronizedMethodThread b = new SynchronizedMethodThread(object);
        b.setName("B");

        a.start();
        b.start();

        Thread.sleep(1000 * 15);
    }
}
