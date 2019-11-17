package com.ca.fire.test.jvm.heap;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JavaHeapTest {



    public JavaHeapTest() {
    }

    public final static int OUTOFMEMORY = 200000000;

    private String oom;

    private int length;

    StringBuffer tempOOM = new StringBuffer();

    public JavaHeapTest(int leng) {
        this.length = leng;

        int i = 0;
        while (i < leng) {
            i++;
            try {
                tempOOM.append("a");
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                break;
            }
        }
        this.oom = tempOOM.toString();

    }

    public void test01() {
        int count = 30;
        while (count > 0) {
            String[] strings = new String[1024 * 1024 * 10];
            System.out.println(count + "==" + strings.length);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
        }

    }

    public String getOom() {
        return oom;
    }

    public int getLength() {
        return length;
    }

//    public static void main(String[] args) {
//        JavaHeapTest javaHeapTest = new JavaHeapTest(OUTOFMEMORY);
//        System.out.println(javaHeapTest.getOom().length());
//        javaHeapTest.test01();
//    }

}