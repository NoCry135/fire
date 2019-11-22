package com.ca.fire.test.java8.optional;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by caian on 2019/11/20
 * 由于orElseGet()不是每次都会调用传入的方法，所以orElseGet()方法在性能上要优于orElse()方法。
 * <p>
 * 一般情况下，使用orElseGet()方法更好，除非默认对象已经定义好可以直接访问。
 */
public class OptionalTest {

    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(10, 20, 30);
        List<Integer> list = Arrays.asList();
        int a = list.stream().reduce(Integer::sum).orElse(get("a"));
        int b = list.stream().reduce(Integer::sum).orElseGet(() -> get("b"));
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    public static int get(String name) {
        System.out.println(name + "执行了方法");
        return 1;
    }

    @Test
    public void test01() {
        String nullValue = null;
        String optional = Optional.ofNullable(nullValue).orElse("Su");
        System.out.println(optional);
        String optionalGet = Optional.ofNullable(nullValue).orElseGet(() -> "Xiao");
        System.out.println(optionalGet);
        String nonNullOptional = Optional.ofNullable("Susan").orElse("Su");
        System.out.println(nonNullOptional);
        String nonNullOptionalGet = Optional.ofNullable("Molly").orElseGet(() -> "Xiao");
        System.out.println(nonNullOptionalGet);
    }
}
