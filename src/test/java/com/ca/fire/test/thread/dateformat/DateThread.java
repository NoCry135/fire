package com.ca.fire.test.thread.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateThread implements Runnable {
    private String format;
    private SimpleDateFormat sdf;

    public DateThread(String format, SimpleDateFormat sdf) {
        this.format = format;
        this.sdf = sdf;
    }

    @Override
    public void run() {
        try {
            Date parse = sdf.parse(format);
            String newDateString = sdf.format(parse);
            if (!newDateString.equals(format)) {
                System.out.println("格式转换异常!日期字符串为" + format + ",转换后的为 " + newDateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
