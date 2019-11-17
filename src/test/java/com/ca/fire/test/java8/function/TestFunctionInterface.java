package com.ca.fire.test.java8.function;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式接口:接口中只有一个抽象方法,使用 @FunctionalInterface注解来约束;
 * 所以使用lambda表达式时,编译器可以根据上下文找到对应的接口,方法,确定参数类型
 */
public class TestFunctionInterface {


    @Test
    public void testConsumer() {
        /**
         * @name消费型接口
         * @use Consumer<T>
         * @Param T 传入参数
         * @fun 接受一个参数, 无返回值
         */
        Consumer<String> con = (x) -> {
            System.out.println("我消费了一个:" + x);
        };
        con.accept("苹果");
    }

    @Test
    public void testSupplier() {
        /**
         * @name 供应式接口
         * @use Supplier<R>
         * @Param R返回值类型
         * @fun 无参数, 有返回值
         */
        Supplier<Date> supplier = () -> new Date();
        Date date = supplier.get();
        System.out.println("获得了一个供应的日期:" + date);
    }

    /**
     * @param T 传入参数
     * @return R 返回参数
     * @name函数型接口
     * @use Function<T   ,   R>
     * @fun 接收一个参数, 有返回值
     */
    @Test
    public void testFunction() {
        Function<String, String> fun = (str) -> "hello," + str;
        String tom = fun.apply("Tom");
        System.out.println(tom);

    }

    /**
     * @param T 入参
     * @return Boolean 返回一个Boolean值
     * @name 断定型接口
     * @use Predicate<T>
     * @fun 接收一个参数, 返回一个Boolean值
     */
    @Test
    public void testPredicate() {
        Predicate<Integer> predicate = (num) -> num > 0;
        System.out.println(predicate.test(5));

    }
}
