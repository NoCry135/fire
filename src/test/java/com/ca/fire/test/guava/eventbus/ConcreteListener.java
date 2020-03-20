package com.ca.fire.test.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Created on 2020/1/17
 */
public class ConcreteListener extends BaseListener {

    @Subscribe
    public void conTask(String event) {
        System.out.println(String.format("ConcreteListener receiving event:%s", event));

    }
}
