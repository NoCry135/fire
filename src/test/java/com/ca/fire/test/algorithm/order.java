package com.ca.fire.test.algorithm;

import org.junit.Test;

public class order {
    private int[] array = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};

    @Test
    public void bubbleSort() {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    @Test
    public void selectSort() {
        int temp, max;
        for (int i = 0; i < array.length; i++) {
            max = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[max]) {
                    max = j;
                }
            }
            if (i != max) {
                temp = array[i];
                array[i] = array[max];
                array[max] = temp;
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    @Test
    public void testQuick() {
        sort(array, 0, array.length -1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public void sort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];


        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key)  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key)//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) sort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        if (end < high) sort(a, end + 1, high);//右边序列。从关键值索引+1到最后一个
    }
}
