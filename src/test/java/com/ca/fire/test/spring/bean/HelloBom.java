package com.ca.fire.test.spring.bean;

public class HelloBom implements Hello {

    @Override
    public void say(String hi) {
        System.out.println(hi + " Bom!");
    }
}
