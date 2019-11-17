package com.ca.fire.test.design.state.unit01;

public class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void speak() {
        if ("hi".equals(state.getValue())) {
            state.sayHi();
        } else if ("hello".equals(state.getValue())) {
            state.sayHello();
        } else {
            throw new IllegalArgumentException("参数非法");
        }
    }
}
