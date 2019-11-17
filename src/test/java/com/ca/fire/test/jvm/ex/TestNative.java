package com.ca.fire.test.jvm.ex;

public class TestNative {
    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
            new Thread(){
                @Override
                public void run() {
                }
            }.start();
        }
    }
}
