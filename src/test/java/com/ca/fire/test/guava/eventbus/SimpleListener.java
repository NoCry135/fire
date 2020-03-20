package com.ca.fire.test.guava.eventbus;

import com.ca.fire.domain.bean.User;
import com.google.common.eventbus.Subscribe;

/**
 * Created on 2020/1/17
 */
public class SimpleListener {

    @Subscribe
    public void doAction(final String event) {
        System.out.println(String.format("SimpleListener event receiving msg:%s", event));
    }

    @Subscribe
    public void consumer(final User user) {
        System.out.println(String.format("SimpleListener event receiving msg:%s", user));
    }

}
