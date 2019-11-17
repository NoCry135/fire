package com.ca.fire.test.design.bridge;

public class MyBridge extends Bridge {
    public void method() {
        getSource().method();
    }
}
