package com.ca.fire.test.design.bridge;

public class SourceSub1 implements Sourceable {
    @Override
    public void method() {
        System.out.println("实现了方法的一种方式");
    }
}
