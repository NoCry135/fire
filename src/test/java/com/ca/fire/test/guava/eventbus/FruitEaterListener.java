package com.ca.fire.test.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Created on 2020/1/17
 */
public class FruitEaterListener {
    @Subscribe
    public void eat(Fruit event) {

        System.out.println(String.format("Fruit eat[{%s}]. ", event));
    }

    @Subscribe
    public void eat(Apple event) {
        System.out.println(String.format("Apple eat[{%s}]. ", event));
    }
}
