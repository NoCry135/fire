package com.ca.fire.test.design.composite.unit03;

public class Disk extends Equipment {

    public Disk(String name) {
        super(name);
    }

    @Override
    public double netPrice() {
        return 1.0;
    }

    @Override
    public double discountPrice() {
        return 0.2;
    }
}
