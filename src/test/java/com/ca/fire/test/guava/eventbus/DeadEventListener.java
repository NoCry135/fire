package com.ca.fire.test.guava.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * Created on 2020/1/17
 */
public class DeadEventListener {
    @Subscribe
    public void handle(DeadEvent event) {
        //获取事件源
        System.out.println(event.getSource());//DEAD-EVENT-BUS
        //获取事件
        System.out.println(event.getEvent());//DeadEventListener event
    }
}
