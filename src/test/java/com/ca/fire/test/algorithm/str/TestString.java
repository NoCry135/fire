package com.ca.fire.test.algorithm.str;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 2020/1/3
 */
public class TestString {
    public static void main(String[] args) {
        int j = 0;
        System.out.println(j++);
    }

    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0;
        int i = 0;
        int j = 0;
        while (i < n && j < n) {
            if (set.add(s.charAt(j++))) {
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }

        return ans;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n <= 1) {
            return n;
        }
        //遍历字符串的每一位，发现重复则停止，记录字符的长度（记录最大长度）
        int max = 0;
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                if (set.add(chars[j])) {
                    continue;
                } else {
                    int size = set.size();
                    if (size > max) {
                        max = size;
                        set.clear();
                        break;
                    }
                }

            }
        }
        return max;

    }
}
