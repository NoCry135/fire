package com.ca.fire.test.thread.unit03;

public class Product implements Runnable {

    private Clert clert;

    public Product(Clert clert) {
        this.clert = clert;
    }

    @Override
    public void run() {
        while (true) {
            clert.product();
        }
    }
}
