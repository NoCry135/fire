package com.ca.fire.test.bigdecimal;

import org.junit.Test;

import java.math.BigDecimal;

public class TestBigDecimal {
    private BigDecimal num1 = new BigDecimal(100);
    private BigDecimal num2 = new BigDecimal(20);

    /**
     * 测试加法
     */
    @Test
    public void testAdd() {
        BigDecimal add = num1.add(num2);
        System.out.println(add);
    }

    /**
     * 测试减法
     */
    @Test
    public void testSub() {
        BigDecimal subtract = num2.subtract(num1);
        System.out.println(subtract);
    }

    /**
     * 测试乘法
     */
    @Test
    public void testMul() {
        BigDecimal multiply = num1.multiply(num2);
        BigDecimal num3 = new BigDecimal(3);
        BigDecimal multiply1 = num1.multiply(num2).multiply(num3);
        BigDecimal multiply2 = multiply1.multiply(multiply);

        System.out.println(multiply1);
        System.out.println(multiply2);
    }

    /**
     * 测试除法
     */
    @Test
    public void testDiv() {
        BigDecimal divide = num1.divide(num2);
        System.out.println(divide);

        divide = num2.divide(num1);
        System.out.println(divide);
    }

    @Test
    public void testCom() {
        //num1大于num2返回1
        System.out.println(num1.compareTo(num2));
        //num2小于num2返回-1
        System.out.println(num2.compareTo(num1));
    }
    @Test
    public void testDiv1() {
        num2 = new BigDecimal(3);
        BigDecimal divide = num1.divide(num2,0,BigDecimal.ROUND_DOWN);
        System.out.println(divide);

        divide = num2.divide(num1);
        System.out.println(divide);
    }

    @Test
    public void testNev() {
        BigDecimal  num2 = new BigDecimal(3);
        BigDecimal negate = num2.negate();
        System.out.println(num2);
        System.out.println(negate);

        BigDecimal diffQty = BigDecimal.ZERO;
        diffQty = diffQty.subtract(num2);
        System.out.println(diffQty);

    }


}
