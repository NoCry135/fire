package com.ca.fire.test.design.decorator.unit02;

import org.junit.Test;

public class TestCake {

    @Test
    public void test01() {
        Pancake cake = new NormalCake();
        cake.say();
    }

    @Test
    public void test02() throws InterruptedException {
        Pancake cake = new NormalCake();
        EggCake eggCake = new EggCake(cake);
        eggCake.say();

    }
}
