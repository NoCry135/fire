package com.ca.fire.test.design.decorator.unit01;

public class Source implements Sourceable {

    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
