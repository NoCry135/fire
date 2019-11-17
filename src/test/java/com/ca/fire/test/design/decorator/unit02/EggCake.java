package com.ca.fire.test.design.decorator.unit02;

public class EggCake implements Pancake {
    private Pancake pancake;

    public EggCake(Pancake pancake) {
        this.pancake = pancake;
    }

    @Override
    public void say() {
        pancake.say();
        System.out.println("加鸡蛋6.5");
    }
}
