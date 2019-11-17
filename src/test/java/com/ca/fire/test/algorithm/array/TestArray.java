package com.ca.fire.test.algorithm.array;

import org.junit.Test;

public class TestArray {

    @Test
    public void test01() {
//        int[][] ints = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] ints = {{1, 2, 3}, {4,5, 6}, {7,8,9}};
        for (int[] num : ints) {   //输出旋转后的数组
            if (num.length % 3 == 0)
                System.out.println("");
            for (int c : num) {
                System.out.print(c + "  ");
            }
            System.out.println();
        }
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                ints[i][j] = ints[2 - j][i];  //顺时针旋转90度，核心算法
                //b[i][j]=a[2-i][2-j] 顺时针旋转180度，核心算法
                //b[i][j]=a[j][2-i]   顺时针旋转270度，核心算法
            }
        }
        for (int[] num : ints) {   //输出旋转后的数组
            if (num.length % 4 == 0)
                System.out.println("");
            for (int c : num) {
                System.out.print(c + "  ");
            }
            System.out.println();
        }
    }
}
