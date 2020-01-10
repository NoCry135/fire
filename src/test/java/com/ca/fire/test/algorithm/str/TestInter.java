package com.ca.fire.test.algorithm.str;

import org.junit.Test;

import java.util.*;

/**
 * Created on 2019/12/26
 * [2,2,1,1,1,2,2]
 */
public class TestInter {

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            Integer times = numCount.get(val);
            if (times == null) {
                numCount.put(val, 1);
            } else {
                times++;
                numCount.put(val, times);
                if (times > nums.length / 2) {
                    return val;
                }
            }
        }
        int max = 0;
        int count = 0;
        Set<Map.Entry<Integer, Integer>> entries = numCount.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {

            Integer value = entry.getValue();
            if (value > count) {
                count = value;
                Integer key = entry.getKey();
                max = key;
            }
        }
        return max;
    }

    @Test
    public void testmajorityElement() {
        int[] arr = new int[]{2, 2, 1, 1, 1, 2, 2};
        int val = majorityElement(arr);
        System.out.println(val);
    }

    public int singleNumber(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                list.remove(nums[i]);
            } else {
                list.add(nums[i]);
            }
        }
        return list.get(0);
    }

    @Test
    public void testsingleNumber() {
        int[] arr = new int[]{4, 1, 2, 2, 4};
        int val = singleNumber(arr);
        System.out.println(val);
    }


    private List<String> output = new ArrayList<>();
    static Map<String, String> map = new HashMap<>();

    static {
        map.put("0", "");
        map.put("1", "");
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
    }

    public List<String> letterCombinations(String digits) {


        if (digits != null && digits != "") {
            backTrack("", digits);
        }
        return output;

    }

    /**
     * String digit = next_digits.substring(0, 1);
     * String letters = phone.get(digit);
     * for (int i = 0; i < letters.length(); i++) {
     * String letter = phone.get(digit).substring(i, i + 1);
     * // append the current letter to the combination
     * // and proceed to the next digits
     * backtrack(combination + letter, next_digits.substring(1));
     * }
     *
     * @param combination
     * @param next_digits
     */
    public void backTrack(String combination, String next_digits) {
        if (next_digits.length() == 0) {
            output.add(combination);
        } else {
            String disit = next_digits.substring(0, 1);
            String letters = map.get(disit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = letters.substring(i, i + 1);
                backTrack(combination + letter, next_digits.substring(1));
            }
        }
    }


    public int myAtoi(String str) {
        if (str == null || str == "") {
            return 0;
        }
        str = str.trim();
        char c = str.charAt(0);
        int flag = 1;
        if (c == '-') {
            flag = flag * -1;
            str = str.substring(1);
        } else if (c == '+') {
            flag = flag;
            str = str.substring(1);
        } else if (!(c >= '0' && c <= '9')) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (c >= '0' && c <= '9') {
                count = count * 10 * i + (ch - 49);
            } else {
                return count;
            }
        }

        return count * flag;
    }

    @Test
    public void test01() {
        List<String> strings = letterCombinations("23");
        System.out.println(strings);
//        System.out.println(myAtoi("12123"));
//        System.out.println(myAtoi("-12123"));
//        System.out.println(myAtoi("W-12123"));
//        System.out.println(myAtoi("1212www3"));
    }
}
