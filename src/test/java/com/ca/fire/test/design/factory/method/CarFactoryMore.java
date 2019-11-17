package com.ca.fire.test.design.factory.method;

/**
 * ，而多个工厂方法模式是提供多个工厂方法，分别创建对象
 */
public class CarFactoryMore {
    public Icar produceBMW() {
        return new BMWCar();
    }

    public Icar produceAudo() {
        return new AudoCar();
    }
}
