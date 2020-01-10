package com.ca.fire.test.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 2019/12/24
 */
public class TestSet {

    @Test
    public void test01(){
        String str = "2121";
        System.out.println(str.charAt(2));
        System.out.println(str.charAt(3));
        Set<String> logicZoneSet = new HashSet<String>();
        logicZoneSet.add(null);
        logicZoneSet.add(null);

    }
    public static void main(String[] args) {

        List<String> set = new ArrayList<>();
        set.add("222");
        set.add("111");
        set.add("2333");
        set.add("3444");
        set.add("6666");
        for (int i = set.size() - 1; i >= 0; i--) {
            String s = set.get(i);
            if (s.equals("222") || s.equals("111")) {
                System.out.println(s);
            } else {
                set.remove(s);
            }
        }
        System.out.println(set);
    }
}
