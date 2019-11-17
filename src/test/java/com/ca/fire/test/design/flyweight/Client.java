package com.ca.fire.test.design.flyweight;

public class Client {

    public static void main(String[] args) {
        FlyweightFactory flyweightFactory = new FlyweightFactory();

        Flyweight factory = flyweightFactory.factory(1);
        factory.operation("1234567");

        factory = flyweightFactory.factory(2);
        factory.operation("7654321");

        factory = flyweightFactory.factory(2);
        factory.operation("qwertyu");

    }
}
