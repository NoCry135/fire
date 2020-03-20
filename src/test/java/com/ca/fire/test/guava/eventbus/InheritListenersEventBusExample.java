package com.ca.fire.test.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * Created on 2020/1/17
 * 注册了一个Listener，使用eventBus发送消息它的父类的Subscribe也会对此消息进行处理
 */
public class InheritListenersEventBusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        ConcreteListener concreteListener = new ConcreteListener();
        eventBus.register(concreteListener);
        System.out.println("post the string event.");
        eventBus.post("I am String event");
        System.out.println("post the int event.");
        eventBus.post(1000);
    }
}
