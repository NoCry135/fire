package com.ca.fire.test.design.factory.method;

/**
 * ，在普通工厂方法模式中，如果传递的字符串出错，则不能正确创建对象
 */
public class CarFactory {

    public Icar pruduce(String type) {

        if ("bmw".equals(type)) {
            return new BMWCar();
        } else if ("audo".equals(type)) {
            return new AudoCar();
        } else {
            throw new IllegalArgumentException("参数非法");
        }
    }
}

