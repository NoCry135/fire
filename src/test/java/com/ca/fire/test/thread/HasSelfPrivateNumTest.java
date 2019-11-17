package com.ca.fire.test.thread;

import org.junit.Test;

public class HasSelfPrivateNumTest {

    @Test
    public void test01() throws InterruptedException {
        HasSelfPrivateNum numA = new HasSelfPrivateNum();
        HasSelfPrivateNum numB = new HasSelfPrivateNum();
        SelfPrivateThreadA threadA = new SelfPrivateThreadA(numA);
        threadA.start();
        SelfPrivateThreadB threadB = new SelfPrivateThreadB(numB);
        threadB.start();

        Thread.sleep(1000 * 3);
    }
}
