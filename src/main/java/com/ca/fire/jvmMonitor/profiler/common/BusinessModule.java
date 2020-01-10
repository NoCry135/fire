package com.ca.fire.jvmMonitor.profiler.common;

import com.ca.fire.jvmMonitor.profiler.util.CacheUtil;
import com.ca.fire.jvmMonitor.profiler.util.CustomLogger;

/**
 * Created on 2019/12/1
 */
public class BusinessModule {
    public BusinessModule() {
    }

    public static void businessHandle(String key, int type, int value, String detail) {
        try {
            key = strPreHandle(key);
            detail = strPreHandle(detail);
            if (detail.length() > 512) {
                detail = detail.substring(0, 512);
            }

            CustomLogger.BusinessLogger.info("{\"time\":\"" + CacheUtil.getNowTime() + "\"" + ",\"key\":" + "\"" + key + "\"" + ",\"hostname\":" + "\"" + CacheUtil.HOST_NAME + "\"" + ",\"type\":" + "\"" + type + "\"" + ",\"value\":" + "\"" + value + "\"" + ",\"detail\":" + "\"" + detail + "\"}");
        } catch (Exception var5) {
        }

    }

    public static void businessHandle(String key, int type, int value, String detail, String rtxList, String mailList, String smsList) {
        try {
            key = strPreHandle(key);
            detail = strPreHandle(detail);
            if (detail.length() > 512) {
                detail = detail.substring(0, 512);
            }

            if (null == rtxList) {
                rtxList = "";
            } else {
                rtxList = strPreHandle(rtxList);
            }

            if (null == mailList) {
                mailList = "";
            } else {
                mailList = strPreHandle(mailList);
            }

            if (null == smsList) {
                smsList = "";
            } else {
                smsList = strPreHandle(smsList);
            }

            CustomLogger.BusinessLogger.info("{\"time\":\"" + CacheUtil.getNowTime() + "\"" + ",\"key\":" + "\"" + key + "\"" + ",\"hostname\":" + "\"" + CacheUtil.HOST_NAME + "\"" + ",\"type\":" + "\"" + type + "\"" + ",\"value\":" + "\"" + value + "\"" + ",\"detail\":" + "\"" + detail + "\"" + ",\"RTX\":" + "\"" + rtxList + "\"" + ",\"MAIL\":" + "\"" + mailList + "\"" + ",\"SMS\":" + "\"" + smsList + "\"}");
        } catch (Exception var8) {
        }

    }

    private static String strPreHandle(String str) {
        try {
            str = str.replace("\r\n", " ");
            str = str.replace("\r", " ");
            str = str.replace("\n", " ");
            str = str.replace("\\", "\\\\");
            str = str.replace("\"", "\\\"");
            str = str.trim();
        } catch (Exception var2) {
        }

        return str;
    }
}
