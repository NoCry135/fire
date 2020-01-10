package com.ca.fire.jvmMonitor.profiler.common;

import com.ca.fire.jvmMonitor.profiler.jvm.JvmInfoPickerFactory;
import com.ca.fire.jvmMonitor.profiler.util.CacheUtil;
import com.ca.fire.jvmMonitor.profiler.util.CustomLogger;
import com.ca.fire.jvmMonitor.profiler.util.LogFormatter;

import java.util.Map;

/**
 * Created on 2019/12/1
 */
public class CommonModule {
    private static final String INSTANCE_ID = JvmInfoPickerFactory.create("Local").getJvmInstanceCode();
    private static final String LOG_TEMPLATE = "{\"time\":\"{}\",\"host\":\"" + CacheUtil.HOST_NAME + "\",\"ip\":\"" + CacheUtil.HOST_IP + "\",\"iCode\":\"" + INSTANCE_ID + "\",\"type\":\"{}\",\"data\":{}}";

    public static void log(String type, String data) {
        CustomLogger.CommonLogger.info(LogFormatter.format(LOG_TEMPLATE, new Object[]{CacheUtil.getNowTime(), type, data}));
    }

    public static void log(String type, Map<String, String> data) {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            sb.append("\"").append((String) entry.getKey()).append("\"").append(":").append("\"").append((String) entry.getValue()).append("\"").append(",");
        }
        sb.deleteCharAt(sb.length() - 1).append("}");

        CustomLogger.CommonLogger.info(LogFormatter.format(LOG_TEMPLATE, new Object[]{CacheUtil.getNowTime(), type, sb.toString()}));
    }
}
