package com.ca.fire.test.thread.dateformat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSimpleDateFormat {

    public static void main(String[] args) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = Arrays.asList("2000-01-01", "2000-01-02", "2000-01-03", "2000-01-04", "2000-01-05", "2000-01-06", "2000-01-07", "2000-01-08", "2000-01-09", "2000-01-10");
        List<Thread> dateThreads = new ArrayList<>();
        for (String s : list) {

//            dateThreads.add(new Thread(new DateThread(s, simpleDateFormat)));
            dateThreads.add(new Thread(new SafeDateThread(s, simpleDateFormat)));
        }
        for (Thread dateThread : dateThreads) {
            dateThread.start();
        }

    }
}
