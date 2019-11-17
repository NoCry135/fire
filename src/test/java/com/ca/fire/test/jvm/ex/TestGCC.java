package com.ca.fire.test.jvm.ex;

import java.util.ArrayList;

public class TestGCC {


    public static void main(String[] args) {
      int i = 0;
        ArrayList<String> objects = new ArrayList<>();
        while (true){

            objects.add(String.valueOf(i++).intern());
        }
    }
}
