package com.ca.fire.jvmMonitor.profiler.common;

import com.ca.fire.jvmMonitor.profiler.jvm.JvmInfoPickerFactory;
import com.ca.fire.jvmMonitor.profiler.util.CacheUtil;
import com.ca.fire.jvmMonitor.profiler.util.CustomLogger;
import com.ca.fire.jvmMonitor.profiler.util.LogFormatter;

import java.util.Map;

/**
 * Created on 2019/12/1
 */
public class BizModule {
    private static final String LOG_TYPE = "BIZ";
    private static final String PROC_TYPE = "4";
    private static final String INSTANCE_ID = JvmInfoPickerFactory.create("Local").getJvmInstanceCode();
    private static final String BIZ_LOG_TEMPLATE1 = "{\"k\":\"{}\",\"ty\":\"{}\",\"{}\":\"{}\"}";
    private static final String BIZ_LOG_TEMPLATE2 = "{\"k\":\"{}\",\"ty\":\"{}\"{}}";
    private static final String PROC_LOG_TEMPLATE = "{\"k\":\"{}\",\"ty\":\"4\",\"i\":\"" + INSTANCE_ID + "\",\"d\":{}}";

    public static void bizHandle(String key, int type, Number value) {
        try {
            CustomLogger.BizLogger.info(LogFormatter.format(BIZ_LOG_TEMPLATE1, new Object[]{CacheUtil.getNowTime(), key, type, type == 1 ? "bValue" : "bCount", value}));
        } catch (Exception localException) {
        }
    }

    public static void bizHandle(String key, int type, Map<String, ?> dataMap) {
        try {
            StringBuilder sb = new StringBuilder(32);
            for (Map.Entry<String, ?> entry : dataMap.entrySet()) {
                sb.append(",").append("\"").append((String) entry.getKey()).append("\"").append(":").append("\"").append(entry.getValue()).append("\"");
            }
            Object[] args = {key, Integer.valueOf(type), sb.toString()};

            CustomLogger.BizLogger.info(LogFormatter.format(BIZ_LOG_TEMPLATE2, args));

        } catch (Exception localException) {
        }
    }

    public static void bizNode(String nodeID, String data) {
        CustomLogger.BizLogger.info(LogFormatter.format(PROC_LOG_TEMPLATE, new Object[]{CacheUtil.getNowTime(), nodeID, data}));
    }

    public static void bizNode(String nodeID, Map<String, String> data) {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            sb.append("\"").append((String) entry.getKey()).append("\"").append(":").append("\"").append((String) entry.getValue()).append("\"").append(",");
        }
        int length = sb.length();
        if (length > 1) {
            sb.deleteCharAt(length - 1);
        }
        sb.append("}");

        Object[] args = {nodeID, sb.toString()};

        CustomLogger.BizLogger.info(LogFormatter.format(PROC_LOG_TEMPLATE, args));
    }
}
