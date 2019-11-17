package com.ca.fire.test.design.strategy.unit03;

public class NoFly implements Flyable {

    @Override
    public void fly() {
        System.out.println("no fly...");
    }
}
