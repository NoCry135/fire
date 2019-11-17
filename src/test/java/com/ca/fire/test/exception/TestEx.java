package com.ca.fire.test.exception;

public class TestEx {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o.toString().getBytes());
        myMethod();
        System.out.println(test());
    }

    public static int test() {
        int a = 100;
        int i = 10;

        try {
            int j = i / 0;
        } catch (Exception e) {
            e.printStackTrace();
            a = 200;

//            return a;
        } finally {

            a = 300;
            int j = i / 0;

        }

        return a;
    }

    public static int myMethod() {

        int i = 0;
        int[] num = {1, 2, 3};

        System.out.println(num[3]);

        try {
            System.out.println("try 代码块被执行！");
            return 0;
        } catch (Exception e) {
            System.out.println("catch 代码块被执行！");
            return 0;
        } finally {
            System.out.println("finally 代码块被执行！");
        }

    }
}
