package com.ca.fire.test.spi;

import java.util.ServiceLoader;

public class SPITest {
    public static void main(String[] args) {
        ServiceLoader<HelloInterface> helloInterfaces = ServiceLoader.load(HelloInterface.class);
        if (helloInterfaces != null) {
            for (HelloInterface helloInterface : helloInterfaces) {
                helloInterface.sayhello();
            }
        } else {
            System.out.println("no interface");
        }

    }
}
