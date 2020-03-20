package com.ca.fire.test.guava.eventbus;

import com.ca.fire.domain.bean.User;
import com.google.common.eventbus.Subscribe;

/**
 * Created on 2020/1/17
 */
public class MultipleEventListeners {

    @Subscribe
    public void conTask1(String event) {
        System.out.println(String.format("ConcreteListener conTask1 receiving event:%s", event));
    }

    @Subscribe
    public void conTask2(String event) {
        System.out.println(String.format("ConcreteListener conTask2 receiving event:%s", event));
    }
    @Subscribe
    public void consumer(final User user) {
        System.out.println(String.format("MultipleEventListeners consumer receiving msg:%s", user));
    }

}
