package com.ca.fire.test.guava.eventbus;

/**
 * Created on 2020/1/17
 */
public class Fruit {

    private final String name;

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                '}';
    }
}
