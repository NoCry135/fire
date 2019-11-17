package com.ca.fire.test.spi.impl;

import com.ca.fire.test.spi.HelloInterface;

public class TextHello implements HelloInterface {
    @Override
    public void sayhello() {
        System.out.println("text hello!");
    }
}
