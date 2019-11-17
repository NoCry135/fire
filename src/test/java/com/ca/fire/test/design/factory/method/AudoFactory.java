package com.ca.fire.test.design.factory.method;

public class AudoFactory implements CarAbsFactory {
    @Override
    public Icar produceCar() {
        return new AudoCar();
    }
}
