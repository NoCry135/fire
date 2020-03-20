package com.ca.fire.test.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * Created on 2020/1/17
 * 当作为参数的event之间有继承关系时，使用eventBus发送消息，eventt的父类listener也会对此消息进行处理。
 */
public class InheritEventsBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());
        eventBus.post(new Apple("apple"));

        System.out.println("---------------------");
        eventBus.post(new Fruit("Fruit"));
    }
}
