package com.ca.fire.test.jvm.heap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestHeap {

    static class MyHeap {
        public MyHeap() {
        }
    }

    @Test
    public void test02() {
        List<Object> list = new ArrayList<>();
        while (true) {

            list.add(new MyHeap());
        }

    }
}
