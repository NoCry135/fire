package com.ca.fire.test.collections;

import java.util.*;

public class MapValueComparator<T extends Comparable<T>> implements Comparator<String> {
    private Map<String, T> map = null;

    public MapValueComparator(Map<String, T> map) {
        this.map = map;
    }

    @Override
    public int compare(String o1, String o2) {
        int r = map.get(o2).compareTo(map.get(o1));
        // 不这样写，值相同的会被删掉；但是这样写，get会返回null。看自己的需求写吧。
        if (r == 0) return 1;
        return r;
    }

    public static void main(String[] args) {
        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("a", 11);
        map2.put("e2", 1);
        map2.put("bd", 3);
        map2.put("3c", 2);
        map2.put("d", 2);

        System.out.println(map2);
        Map<String, Integer> map3 = new TreeMap<String, Integer>(new MapValueComparator<Integer>(map2));
        map3.putAll(map2);

        System.out.println(map3);
        System.out.println(map3.get("d"));
        System.out.println(map3.get("3c"));
        Set<String> strings = map3.keySet();
        System.out.println(strings);

        Map m1 = new HashMap<String, Integer>();
        m1.put("wqwq", 11);
        System.out.println(m1);
    }
}