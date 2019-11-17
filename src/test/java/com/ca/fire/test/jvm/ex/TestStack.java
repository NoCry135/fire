package com.ca.fire.test.jvm.ex;

public class TestStack {

    public static void  test(){
        test();
    }

    public static void main(String[] args) {

        test();
    }
}
