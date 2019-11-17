package com.ca.fire.test.thread.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SafeDateThread implements Runnable {

    private String date;
    private SimpleDateFormat sdf;

    public SafeDateThread(String date, SimpleDateFormat sdf) {
        this.date = date;
        this.sdf = sdf;
    }

    @Override
    public void run() {
        Date parse = null;
        try {
//            parse = DateTools.parse("yyyy-MM-dd", date);
//            String newDateString = DateTools.format("yyyy-MM-dd", parse);
            parse = DateTools.getSimpleDateFormat("yyyy-MM-dd").parse(date);
            String newDateString = DateTools.getSimpleDateFormat("yyyy-MM-dd").format(parse);
            if (!newDateString.equals(date)) {
                System.out.println("格式转换异常!日期字符串为" + date + ",转换后的为 " + newDateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

class DateTools {

    public static Date parse(String format, String date) throws ParseException {
        return new SimpleDateFormat(format).parse(date);
    }

    public static String format(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    private static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>();

    public static SimpleDateFormat getSimpleDateFormat(String dormat) {
        SimpleDateFormat sdf = null;
        sdf = t1.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat(dormat);
            t1.set(sdf);
        }
        return sdf;


    }
}
