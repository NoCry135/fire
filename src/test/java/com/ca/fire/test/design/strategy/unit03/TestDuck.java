package com.ca.fire.test.design.strategy.unit03;

public class TestDuck {

    public static void main(String[] args) {
        Flyable flyable = new CanFly();
        Flyable noFly = new NoFly();
        Quackable gaGaQuack = new GaGaQuack();

        YellowDuck yellowDuck = new YellowDuck();
        yellowDuck.setFlyable(flyable);
        yellowDuck.setQuackable(gaGaQuack);
        yellowDuck.perform();
        yellowDuck.setFlyable(noFly);
        yellowDuck.perform();
    }
}
