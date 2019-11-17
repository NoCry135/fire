package com.ca.fire.test.sort;

import org.junit.Test;

public class TestHeap {

    private int[] nums = {10, 22, 3, 5, 17, 44, 28, 31, 34, 40};

    @Test
    public void test01() {

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {

                if (nums[j] < nums[j + 1]) {
                    int num = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = num;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
    }

    @Test
    public void test02() {

        for (int i = 0; i < nums.length; i++) {
            int maxIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < nums[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                int num = nums[i];
                nums[i] = nums[maxIndex];
                nums[maxIndex] = num;
            }

        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
    }

    public void sort(int[] array, int low, int high) {
        int[] nums = {10, 22, 3, 5, 17, 44, 28, 31, 34, 40};

        if (low >= high) {
            return;
        }
        int index = array[low];
        int i = low;
        int j = high;
        while (i != j) {
            while (i < j && array[j] >= index) {
                j--;
            }
            while (i < j && array[i] <= index) {
                i++;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
            System.out.println();
        }
        array[low] = array[i];
        array[i] = index;
        sort(array, low, i - 1);
        sort(array, i + 1, high);

    }

    @Test
    public void test03() {
        sort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
    }
}
