package com.ca.fire.test.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Created on 2020/1/17
 */
public abstract class AbstractListener {

    @Subscribe
    public void commonTask(String event) {
        System.out.println(String.format("AbstractListener receiving event:%s", event));
    }
}
