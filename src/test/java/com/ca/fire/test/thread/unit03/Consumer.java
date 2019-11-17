package com.ca.fire.test.thread.unit03;

public class Consumer implements Runnable {
    private Clert clert;

    public Consumer(Clert clert) {
        this.clert = clert;
    }

    @Override
    public void run() {
        while (true) {
            clert.consume();
        }
    }
}
