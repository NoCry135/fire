package com.ca.fire.test.design.factory.method;

/**
 * 将上面的多个工厂方法模式里的方法置为静态的，不需要创建实例
 */
public class CarStaticFactory {

    public static Icar produceBMW() {
        return new BMWCar();
    }

    public static Icar produceAudo() {
        return new AudoCar();
    }
}
