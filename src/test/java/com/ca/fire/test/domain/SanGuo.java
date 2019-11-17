package com.ca.fire.test.domain;

public class SanGuo extends Book {

    public String color;


    public static void main(String[] args) {
        Book sanGuo = new SanGuo();
        String color = sanGuo.color;
        System.out.println(color);
    }
}
