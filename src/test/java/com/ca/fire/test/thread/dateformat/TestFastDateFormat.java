package com.ca.fire.test.thread.dateformat;

import org.apache.commons.lang.time.FastDateFormat;

import java.util.Date;

public class TestFastDateFormat {

    public static void main(String[] args) {

        Date date = new Date();
        FastDateFormat fastDateFormat =  FastDateFormat.getInstance("yyyy-MM-dd");
        String format = fastDateFormat.format(date);
        System.out.println(format);

    }
}
