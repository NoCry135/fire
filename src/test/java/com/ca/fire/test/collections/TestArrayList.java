package com.ca.fire.test.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestArrayList {

    public int singleNumber(int[] nums) {
        List<Integer> num1 = new ArrayList<>();
        List<Integer> num2 = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (num1.contains(nums[i])) {
                num2.add(nums[i]);
            } else {
                num1.add(nums[i]);
            }
        }
        num1.removeAll(num2);
        return num1.get(0);
    }

    @Test
    public void test01() {
        ArrayList<Object> objects = null;
        objects = new ArrayList<>(11);
        objects.add("1");
        objects.add("2");
        objects.add("3");
        objects.add("4");
        objects.add("5");
        objects.add("6");
        objects.add("7");
        objects.add("8");
        objects.add("9");
        objects.add("10");
        objects.add("11");
        objects.add("12");
        objects.add("13");
    }

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<Object>();
        final int N = 10000000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法前：" + (endTime - startTime));

        list = new ArrayList<Object>();
        long startTime1 = System.currentTimeMillis();
        list.ensureCapacity(N);
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法后：" + (endTime1 - startTime1));
    }
}
