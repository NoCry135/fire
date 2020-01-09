package com.ca.fire.test.algorithm.str;

import org.junit.Test;

import java.util.*;

public class TestSum {
    private ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
    private int[] nums;

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        this.nums = nums;
        int max_index = 0;
        for (int i = 0; i < k; i++) {
            clean_dek(i, k);
            arrayDeque.addLast(i);
            if (nums[i] > nums[max_index]) {
                max_index = i;
            }

        }
        int[] output = new int[n - k + 1];
        output[0] = nums[max_index];
        for (int i = k; i < n; i++) {
            clean_dek(i, k);
            arrayDeque.addLast(i);
            output[i - k + 1] = nums[arrayDeque.getFirst()];
        }
        return output;


    }

    private void clean_dek(int i, int k) {
        if (!arrayDeque.isEmpty() && arrayDeque.getFirst() == i - k) {
            arrayDeque.removeFirst();
        }
        while (!arrayDeque.isEmpty() && nums[i] > nums[arrayDeque.getLast()]) {
            arrayDeque.removeLast();
        }
    }


    @Test
    public void testisAnagram() {
        boolean anagram = isAnagram("nl", "cx");
        System.out.println(anagram);
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char s1 = s.charAt(i);
            char t1 = t.charAt(i);
            nums[s1 - 'a']++;
            nums[t1 - 'a']--;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testfirstUniqChar() {
        System.out.println(firstUniqChar("loveleetcode"));
    }

    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new TreeMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer count = map.get(ch);
            if (count == null) {
                map.put(ch, 1);
            } else {
                map.put(ch, count++);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            Integer times = map.get(s.charAt(i));
            if (times == 1) {
                return i;
            }
        }
        return -1;
    }


    @Test
    public void test011() {
        voidletterCombinations("11");
    }

    public void voidletterCombinations(String digits) {
        char[] chars = digits.toCharArray();
        for (char ch : chars) {
            System.out.println((int) ch - 48);
            System.out.println(getList((int) ch));
        }
    }

    public List<String> getList(Integer num) {
        Map<Integer, List<String>> hashMap = new HashMap<>();
        hashMap.put(1, Arrays.asList(""));
        hashMap.put(2, Arrays.asList("a", "b", "c"));
        hashMap.put(3, Arrays.asList("d", "e", "f"));
        hashMap.put(4, Arrays.asList("g", "h", "i"));
        hashMap.put(5, Arrays.asList("j", "k", "l"));
        hashMap.put(6, Arrays.asList("m", "n", "o"));
        hashMap.put(7, Arrays.asList("p", "q", "r", "s"));
        hashMap.put(8, Arrays.asList("t", "u", "v"));
        hashMap.put(9, Arrays.asList("w", "x", "y", "z"));
        return hashMap.get(num);
    }


    public boolean isValid(String s) {
        if (s == null || s == "") {
            return false;
        }
        Stack<String> stack = new Stack<>();
        Map<String, String> map = new HashMap<>();
        map.put(")", "(");
        map.put("]", "[");
        map.put("}", "{");
        char[] chArry = s.toCharArray();
        for (int i = 0; i < chArry.length; i++) {
            String c = chArry[i] + "";
            if (!map.containsKey(c)) {
                stack.push(c);
            } else {
                String s1 = stack.empty() ? "#" : stack.pop();
                if (!map.get(c).equals(s1)) {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    public boolean isPalindrome(int x) {
        String str = x + "";
        String revese = new StringBuilder(str).reverse().toString();
        return str.equals(revese);
    }

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
