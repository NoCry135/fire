package com.ca.fire.test.spring.bean;

public abstract class AbsSayHello {


    public void say(String hi) {
        getHello().say(hi);
    }

    /**
     * 这个是抽象的
     *
     * @return
     */
    public abstract Hello getHello();
}
