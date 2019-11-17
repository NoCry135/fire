package com.ca.fire.test.java8.lambda;


import com.ca.fire.test.domain.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.function.*;

/**
 * 方法引用:若Lambda体中的实现,已经有方法提供了功能,此时可以使用方法引用代替Lambda表达式(方法引用是Lambda表达式的另外一种形式)
 * <p>
 * 1. 对象 :: 实例方法名
 * 2. 类名 :: 静态方法名
 * 3 类名 :: 实例方法名
 * <p>
 * 注意:方法引用的那个方法的参数列表与返回值类型,需要与函数式接口中的抽象方法的参数列表与返回值类型保持一致
 * 若Lambda的参数列表第一个参数是方法的调用者,第二个参数是方法引用的参数时(无参),可以使用ClassName :: MethodName
 */
public class TestMethodRef {

    /**
     * 对象::实例方法名
     */
    @Test
    public void test01() {
        PrintStream out = System.out;
        Consumer<String> con = str -> out.println(str);
        con.accept("今天天气不错");

        /**
         * 使用对象::实例方法,参数都省了
         */
        Consumer<String> consumer = out::println;
        consumer.accept("心情美美哒!");

        /**
         * 测试普通方法
         */
        Employee employee = new Employee();
        employee.setName("高晓松");
        Supplier<String> con1 = employee::getName;
        System.out.println(con1.get());

    }

    /**
     * 类名 :: 静态方法名
     */
    @Test
    public void test02() {
        BinaryOperator<Double> binaryOperator = (x, y) -> Math.max(x, y);
        System.out.println(binaryOperator.apply(18.8, 20.0));

        BinaryOperator<Double> binaryOperator1 = Math::max;
        System.out.println(binaryOperator.apply(18.8, 20.0));
    }

    /**
     * 第一个参数为调用者第二个位函数的参数,如equals
     */
    @Test
    public void test03() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        BiPredicate<String, String> biPredicate1 = String::equals;
        System.out.println(biPredicate.test("abf", "abc"));
        System.out.println(biPredicate1.test("abc", "abc"));
    }

    /**
     * 类名::实例方法
     */
    @Test
    public void test04() {
        /**
         * before
         */
        Function<Employee, Integer> function = (e) -> e.getAge();
        Employee lucy = new Employee("Lucy", 12, 3000.0);
        System.out.println(function.apply(lucy));

        Function<Employee, Integer> function1 = Employee::getAge;
        System.out.println(function1.apply(lucy));
    }

    /**
     * 构造器引用
     */
    @Test
    public void test05() {

        Supplier<Employee> supplier = () -> new Employee("Tom",34,500.0);
        System.out.println(supplier.get());
        supplier = Employee::new;
        System.out.println(supplier.get());

        Function<String,Employee> function = Employee::new;
        System.out.println(function.apply("Sam"));
    }
}
