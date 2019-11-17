package com.ca.fire.test.design.strategy.unit03;

/**
 * 把变化的部分抽取出来,成接口;
 * 吧接口注入
 */
public abstract class Duck {

    private Flyable flyable;

    private Quackable quackable;

    public void fly() {
        flyable.fly();
    }

    public void quack() {
        quackable.quack();
    }

    public abstract void color();

    public void swim() {
        System.out.println("游呀游!");
    }

    public Flyable getFlyable() {
        return flyable;
    }

    public void setFlyable(Flyable flyable) {
        this.flyable = flyable;
    }

    public Quackable getQuackable() {
        return quackable;
    }

    public void setQuackable(Quackable quackable) {
        this.quackable = quackable;
    }

    public void perform() {
        color();
        swim();
        fly();
        quack();
    }
}
