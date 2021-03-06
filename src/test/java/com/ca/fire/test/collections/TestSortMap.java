package com.ca.fire.test.collections;

import org.junit.Test;

import java.util.*;

public class TestSortMap {

    public static Map<Integer, Double> Probs = new TreeMap<Integer, Double>();

    public static Map<String, Integer> prop = new TreeMap<String, Integer>();

    @Test
    public void test01() {
        prop.put("a", 1);
        prop.put("e2", 1);
        prop.put("bd", 3);
        prop.put("3c", 2);
        prop.put("d", 2);
        Map<String, Integer> stringIntegerMap = sortByValueDescending(prop);
        System.out.println(stringIntegerMap);
        System.out.println(stringIntegerMap.get("d"));

    }

    public static void main(String[] args) {
        Probs.put(1, 0.5);
        Probs.put(2, 1.5);
        Probs.put(3, 0.2);
        Probs.put(4, 10.2);
        Probs = sortByValueDescending(Probs);
        System.out.println("基于value值的降序，排序输出结果为：");
        for (Map.Entry<Integer, Double> entry : Probs.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        System.out.println();
        System.out.println("基于value值的升序，排序输出结果为：");
        Probs = sortByValueAscending(Probs);
        for (Map.Entry<Integer, Double> entry : Probs.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }


    }

    //降序排序
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return -compare;
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    //升序排序
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueAscending(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return compare;
            }
        });

        //有序
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
