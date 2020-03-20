package com.ca.fire.test.guava.eventbus;

import com.ca.fire.domain.bean.User;
import com.google.common.eventbus.EventBus;

/**
 * Created on 2020/1/17
 * eventBus会根据Listener的参数类型的不同，分别向不同的Subscribe发送不同的消息。
 */
public class MultipleEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new MultipleEventListeners());
        System.out.println("post the string event.");
        eventBus.post("I am String event");
        User user = new User();
        user.setUserName("tom");
        user.setEmail("tom@qq.com");
        eventBus.post(user);
    }
}
