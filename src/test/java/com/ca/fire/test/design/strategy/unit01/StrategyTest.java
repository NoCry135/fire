package com.ca.fire.test.design.strategy.unit01;

public class StrategyTest {

    public static void main(String[] args) {
        String exp = "2+8";
        ICalculator cal = new Plus();
        int result = cal.calculate(exp);
        System.out.println(result);

        String exp1 = "12-8";
        ICalculator minus = new Minus();
        result = minus.calculate(exp1);
        System.out.println(result);
    }
}
