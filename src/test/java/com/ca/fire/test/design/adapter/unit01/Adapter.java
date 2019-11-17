package com.ca.fire.test.design.adapter.unit01;

public class Adapter extends Source implements Targetable {


    @Override
    public void method2() {
        System.out.println("invoke method 2");
    }
}
