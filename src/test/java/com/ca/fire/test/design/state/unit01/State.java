package com.ca.fire.test.design.state.unit01;

public class State {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void sayHello() {
        System.out.println("hello everyone!");
    }

    public void sayHi() {
        System.out.println("Hi everyone!");
    }
}
