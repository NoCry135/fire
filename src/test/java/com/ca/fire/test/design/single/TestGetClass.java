package com.ca.fire.test.design.single;

import org.junit.Test;

public class TestGetClass {

    public static void main(String[] args) {

//        LazySingleClass singleClass1 = LazySingleClass.getSingleClass();
//        LazySingleClass singleClass2 = LazySingleClass.getSingleClass();
//
//        System.out.println("singleClass1 =? singleClass2==>" + (singleClass1 == singleClass2));
//        LazySingleClass singleClass1 = null;
//        LazySingleClass singleClass2 = null;

        Thread thread01 = new Thread("线程1") {
            public void run() {
                System.out.println(LazySingleClass.getSingleClass());
            }
        };

        Thread thread02 = new Thread("线程1") {
            public void run() {
                System.out.println(LazySingleClass.getSingleClass());
            }
        };
        thread01.start();
        try {
            thread01.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread02.start();
    }

    @Test
    public void test01() {
        LazySingleClass singleClass1 = null;
        LazySingleClass singleClass2 = null;

        Thread thread01 = new Thread("线程1") {
            public void run() {
                System.out.println(LazySingleClass.getSingleClass());
            }
        };

        Thread thread02 = new Thread("线程1") {
            public void run() {
                System.out.println(LazySingleClass.getSingleClass());
            }
        };
        thread01.start();
        thread02.start();
    }
}
