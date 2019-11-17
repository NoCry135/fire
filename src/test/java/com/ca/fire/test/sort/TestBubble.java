package com.ca.fire.test.sort;

import org.junit.Test;

public class TestBubble {
    private int[] nums = {10, 22, 3, 5, 17, 44, 28, 31, 34, 40};


    @Test
    public void test01() {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
        System.out.println();

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int a = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = a;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
    }
}
