package com.ca.fire.test.thread;

public class SelfPrivateThreadB extends Thread {

    private HasSelfPrivateNum num;

    public SelfPrivateThreadB(HasSelfPrivateNum num) {
        this.num = num;
    }

    @Override
    public void run() {
        num.addI("b");
    }
}
