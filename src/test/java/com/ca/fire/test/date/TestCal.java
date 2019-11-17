package com.ca.fire.test.date;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class TestCal {

    @Test
    public void test01() {
        Date date = new Date();
        System.out.println(date);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        System.out.println(calendar.getTime());
    }

    public static void main(String[] args) {

        System.out.println("start my app");
        int count = 0;
        while (true) {
            System.out.println("hello ,current value is :" + count++);
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
