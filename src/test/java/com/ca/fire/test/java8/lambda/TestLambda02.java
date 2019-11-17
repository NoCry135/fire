package com.ca.fire.test.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

public class TestLambda02 {
    public static void main(String[] args) {

    }

    /**
     * 原来使用内部类作为参数传递
     */
    @Test
    public void test02() {
        /**
         * 原来使用内部类作为参数传递
         */
        TreeSet<String> ts01 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        String s1 = "abc";
        String s2 = "fghg";
        ts01.add(s1);
        ts01.add(s2);
        for (String t : ts01) {
            System.out.println(t);
        }

        /**
         * lambda表达式1
         */
        TreeSet<String> ts02 = new TreeSet<>((x, y) -> {
            return Integer.compare(x.length(), y.length());
        });
        ts02.add(s1);
        ts02.add(s2);
        for (String t : ts02) {
            System.out.println(t);
        }
        /**
         * lambda表达式2
         */
        TreeSet<String> ts03 = new TreeSet<>((x, y) ->
                Integer.compare(x.length(), y.length())
        );

        ts03.add(s1);
        ts03.add(s2);
        for (String t : ts03) {
            System.out.println(t);
        }
    }

    /**
     * 使用Lambda表达式作为参数传递
     */
    public void test01() {
        TreeSet<String> ts = new TreeSet<>((x, y) -> {
            return Integer.compare(x.length(), y.length());
        });
    }

    public void test03() {
        TreeSet<String> ts = new TreeSet<>((x, y) ->
                Integer.compare(x.length(), y.length())
        );
    }

    //语法1:无参数,无返回值
    public void test04() {
        //传统的方式,创建匿名内部类启动线程
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("anonymous inner class...");
            }
        };

        //使用lambda表达式
        Runnable r2 = () -> System.out.println("Lambda ...");
        //调用了run方法,不是启动线程
        r1.run();
        r2.run();
    }

    //语法2:有一个参数,无返回值(只有一个参数可以去掉小括号)
    @Test
    public void test05() {
        //原先使用匿名内部类调用MyLambda接口的sayHello
        MyLambda myLambda01 = new MyLambda() {
            @Override
            public void sayHello(String str) {
                System.out.println("hello anonymous innner class ..." + str);
            }
        };

        //使用lambda表达式
        MyLambda myLambda02 = (x) -> System.out.println("hello Lambda " + x);

        myLambda01.sayHello("Tom");
        myLambda02.sayHello("Jim");
    }

    @FunctionalInterface
    interface MyLambda {
        void sayHello(String str);
    }

    //语法3:有两个以上的参数,有返回值,并且Lambda表达式有多条语句(需要使用花括号括起来)
    @Test
    public void test06() {
        Comparator<Integer> comparator01 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println("anoyous inner class out");
                return Integer.compare(o1, o2);
            }
        };

        //Lambda表达式
        Comparator<Integer> comparator02 = (x, y) -> {
            System.out.println("Lambda out");
            return Integer.compare(x, y);
        };

        //测试
        System.out.println(comparator01.compare(123, 44));
        System.out.println(comparator02.compare(123, 44));
    }

    //语法4:Lambda体中只有一个语句,return和大括号都可以不写
    @Test
    public void test07() {
        Comparator<Integer> comparator01 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        //Lambda表达式
        Comparator<Integer> comparator02 = (x, y) ->
                Integer.compare(x, y);


        //测试
        System.out.println(comparator01.compare(123, 44));
        System.out.println(comparator02.compare(123, 44));
    }

    //语法5:lambda表达式的参数列表的数据类型可以省略不写,因为jvm编译器可以通过上下文推断出
    @Test
    public void test08() {

        Comparator<Integer> comparator01 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        //Lambda表达式
        Comparator<Integer> comparator02 = (x, y) ->
                Integer.compare(x, y);

        //Lambda表达式
        Comparator<Integer> comparator03 = (Integer x, Integer y) ->
                Integer.compare(x, y);
        Comparator<Integer> comparator04 = Integer::compare;

        //测试
        System.out.println(comparator01.compare(123, 44));
        System.out.println(comparator02.compare(123, 44));
    }


}
