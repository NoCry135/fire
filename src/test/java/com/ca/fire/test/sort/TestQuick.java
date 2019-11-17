package com.ca.fire.test.sort;

import org.junit.Test;

public class TestQuick {
    private int[] nums = {10, 22, 3, 5, 17, 44, 28, 31, 34, 40};


    public void quickSort(int[] arr, int low, int high) {
        if (low >=high) {
            return;
        }
        int index = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (i < j && arr[j] >= index) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }
            while (i < j && arr[i] < index) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
        }
        arr[i] = index;
        quickSort(arr, low, i - 1);
        quickSort(arr, j + 1, high);

    }

    @Test
    public void test01() {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
        quickSort(nums, 0, nums.length - 1);
        System.out.println();
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
    }
}
