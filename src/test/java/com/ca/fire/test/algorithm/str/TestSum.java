package com.ca.fire.test.algorithm.str;

import org.junit.Test;

public class TestSum {

    @Test
    public void test01() {
        String str1 = "7663";
        String str2 = "97653";

        int[] nums1 = string2intArray(str1);
        int[] nums2 = string2intArray(str2);
        int max = nums1.length > nums2.length ? nums1.length : nums2.length;
        int[] up = new int[max + 1];
        System.out.println(up.length);
        int index = 0;
        int index1 = nums1.length - 1;
        int index2 = nums2.length - 1;
        int tmp = 0;
        while (index1 >= 0 && index2 >= 0) {
            int sum = sum(nums1[index1], nums2[index2], tmp);
            if (sum >= 10) {
                tmp = 1;
                up[index] = sum % 10;
            } else {
                tmp = 0;
                up[index] = sum;
            }
            index++;
            index1--;
            index2--;
        }
        while (index1 >= 0) {
            int sum = nums1[index1] + tmp;
            if (sum > 10) {
                tmp = 1;
                up[index] = sum % 10;
            } else {
                tmp = 0;
                up[index] = sum;
            }
            index++;
            index1--;
        }
        while (index2 >= 0) {
            int sum = nums2[index2] + tmp;
            if (sum > 10) {
                tmp = 1;
                up[index] = sum % 10;
            } else {
                tmp = 0;
                up[index] = sum;
            }
            index++;
            index2--;
        }
        if (tmp > 0) {
            up[index] = tmp;
        }
        String sumStr = intArray2Str(up);
        System.out.println("sumStr:" + sumStr);
        System.out.println("direct:" + (Integer.parseInt(str1) + Integer.parseInt(str2)));
    }

    @Test
    public void test02() {
        String str1 = "677663";
        String str2 = "98653";

        int[] nums1 = string2intArray(str1);
        int[] nums2 = string2intArray(str2);
        int max = nums1.length > nums2.length ? nums1.length : nums2.length;
        int[] up = new int[max + 1];
        System.out.println(up.length);
        int index = 0;
        int index1 = nums1.length - 1;
        int index2 = nums2.length - 1;
        int tmp = 0;
        while (index1 >= 0 || index2 >= 0) {
            int sum;
            if (index1 < 0) {
                sum = sum(0, nums2[index2], tmp);
            } else if (index2 < 0) {
                sum = sum(nums1[index1], 0, tmp);
            } else {
                sum = sum(nums1[index1], nums2[index2], tmp);
            }
            if (sum >= 10) {
                tmp = 1;
                up[index] = sum % 10;
            } else {
                tmp = 0;
                up[index] = sum;
            }
            index++;
            index1--;
            index2--;
        }

        if (tmp > 0) {
            up[index] = tmp;
        }
        String sumStr = intArray2Str(up);
        System.out.println("sumStr:" + sumStr);
        System.out.println("direct:" + (Integer.parseInt(str1) + Integer.parseInt(str2)));
    }

    private String intArray2Str(int[] up) {
        StringBuilder sb = new StringBuilder();
        for (int i = up.length - 1; i >= 0; i--) {
            sb.append(up[i]);
        }
        return sb.toString();
    }

    private int[] string2intArray(String str1) {
        int[] ints = new int[str1.length()];
        for (int i = 0; i < str1.length(); i++) {
            String substring = str1.substring(i, i + 1);
            ints[i] = Integer.parseInt(substring);
        }
        return ints;
    }

    private int sum(int c, int c1, int tmp) {

        return c + c1 + tmp;
    }
}
