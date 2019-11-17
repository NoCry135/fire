package com.ca.fire.test.design.strategy.unit01;

public class Minus extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String expression) {
        int[] split = split(expression, "\\-");
        return split[0] - split[1];
    }
}
