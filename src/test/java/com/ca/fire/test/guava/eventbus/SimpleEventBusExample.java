package com.ca.fire.test.guava.eventbus;

import com.ca.fire.domain.bean.User;
import com.google.common.eventbus.EventBus;

/**
 * Created on 2020/1/17
 */
public class SimpleEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        //注册Listener
        SimpleListener listener = new SimpleListener();
        eventBus.register(listener);
        System.out.println("post the simple event.");
        //向订阅者发送消息
        eventBus.post("Simple Event");
        User user = new User();
        user.setUserName("tom");
        user.setEmail("tom@qq.com");
        eventBus.post(user);

    }
}
