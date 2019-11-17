package com.ca.fire.test.thread;

public class SelfPrivateThreadA extends Thread {
    private HasSelfPrivateNum num;

    public SelfPrivateThreadA(HasSelfPrivateNum num) {
        this.num = num;
    }

    @Override
    public void run() {
        num.addI("a");
    }
}
