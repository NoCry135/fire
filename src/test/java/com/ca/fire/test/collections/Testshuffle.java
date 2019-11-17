package com.ca.fire.test.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Testshuffle {

    @Test
    public void test01() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "erf", "g");
        System.out.println(list);
        //洗牌,随机打乱原来的顺序,使用默认随机源对列表进行置换，所有置换发生的可能性都是大致相等的
        Collections.shuffle(list);
        System.out.println(list);

        //static void shuffle(List<?> list, Random rand)
        // 使用指定的随机源对指定列表进行置换，所有置换发生的可能性都是大致相等的，假定随机源是公平的。

        Random random = new Random(10);
        Collections.shuffle(list,random);
        System.out.println(list);
    }
    @Test
    public void test02() {
        int v = new Random().nextInt(100);

        System.out.println(v);

        long l = System.currentTimeMillis();
        System.out.println(l);

    }

}
