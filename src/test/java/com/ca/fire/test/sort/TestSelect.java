package com.ca.fire.test.sort;

import org.junit.Test;

public class TestSelect {

    private int[] nums = {10, 22, 3, 5, 17, 44, 28, 31, 34, 40};

    @Test
    public void test01() {

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i; j < nums.length - i - 1; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            int num = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = num;
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }

    }
}
