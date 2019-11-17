package com.ca.fire.test.spi.impl;

import com.ca.fire.test.spi.HelloInterface;

public class ImageHello implements HelloInterface {
    @Override
    public void sayhello() {
        System.out.println("image hello!");
    }
}
