package com.ca.fire.test.random;

import org.junit.Test;

import java.util.Random;

public class TestRandom {
    @Test
    public void test01() {
        for (int i = 0; i < 100; i++) {
            double random = Math.random();

            System.out.println(random);
        }
    }

    @Test
    public void test02() {
        Random r = new Random(1);
        Random r1 = new Random();
        for (int i = 0; i < 5; i++) {
            int ran1 = r.nextInt(100);
            int ran2 = r1.nextInt(100);
            System.out.println(ran1);
            System.out.println(ran2);
        }
    }

}
