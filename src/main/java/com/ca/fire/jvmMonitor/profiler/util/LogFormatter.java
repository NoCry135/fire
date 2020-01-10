package com.ca.fire.jvmMonitor.profiler.util;


/**
 * Created  on 2019/12/1
 */
public class LogFormatter {
    public static String format(String messagePattern, Object... args) {
        return MessageFormatter.arrayFormat(messagePattern, args);
    }
}
