package com.ca.fire.test.design.strategy.unit01;

/**
 * 设计一个抽象类（可有可无，属于辅助类），提供辅助函数
 */
public abstract class AbstractCalculator {

    int[] split(String expression, String opt) {
        String[] split = expression.split(opt);
        int array[] = new int[2];
        array[0] = Integer.parseInt(split[0]);
        array[1] = Integer.parseInt(split[1]);
        return array;
    }


}
