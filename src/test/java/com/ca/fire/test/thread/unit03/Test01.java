package com.ca.fire.test.thread.unit03;

public class Test01 {

    public static void main(String[] args) {
        Clert clert = new Clert();
        Thread p = new Thread(new Product(clert), "生产者1");
//        Thread p1 = new Thread(new Product(clert), "生产者2");
        Thread c = new Thread(new Consumer(clert), "消费者1");
        Thread c1 = new Thread(new Consumer(clert), "消费者2");
        p.start();
//        p1.start();
        c.start();
        c1.start();

    }
}
