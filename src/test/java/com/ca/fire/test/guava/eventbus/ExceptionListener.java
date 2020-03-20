package com.ca.fire.test.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Created on 2020/1/17
 */
public class ExceptionListener {
    @Subscribe
    public void m1(final String event) {
        System.out.println(String.format("Received event [{%s}] and will take m1", event));

    }

    @Subscribe
    public void m2(final String event) {
        System.out.println(String.format("Received event [{%s}] and will take m2", event));

    }

    @Subscribe
    public void m3(final String event) {
        throw new RuntimeException();
    }
}
