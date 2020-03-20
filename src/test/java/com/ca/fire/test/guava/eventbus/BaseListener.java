package com.ca.fire.test.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Created on 2020/1/17
 */
public class BaseListener extends AbstractListener {

    @Subscribe
    public void baseTask(String event) {
        System.out.println(String.format("BaseListener receiving event:%s", event));

    }
}
