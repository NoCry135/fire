package com.ca.fire.test.design.factory.method;

public class BMWFactory implements CarAbsFactory {
    @Override
    public Icar produceCar() {
        return new BMWCar();
    }
}
