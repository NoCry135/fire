package com.ca.fire.test.java8.function;

@FunctionalInterface
public interface MyFunctionInterface {
    /**
     * 抽象方法
     */
    void sayHello();

    /**
     * 函数接口可以有默认实现
     */
    default void speakEnglish() {
        System.out.println("hello everybody !");
    }
}
