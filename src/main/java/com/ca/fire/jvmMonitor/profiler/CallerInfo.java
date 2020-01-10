package com.ca.fire.jvmMonitor.profiler;

import com.ca.fire.jvmMonitor.profiler.util.CacheUtil;
import com.ca.fire.jvmMonitor.profiler.util.CustomLogger;
import com.ca.fire.jvmMonitor.profiler.util.LogFormatter;

public class CallerInfo {
    public static final int STATE_TRUE = 0;
    public static final int STATE_FALSE = 1;
    private static final String TP_LOG_TEMPLATE;
    private static final String AUTO_TP_LOG_TEMPLATE;
    private long startTime;
    private String key;
    private String appName;
    private int processState;
    private long elapsedTime;

    public CallerInfo(String key) {
        this.key = key;
        this.startTime = System.currentTimeMillis();
        this.elapsedTime = -1L;
        this.processState = 0;
    }

    public CallerInfo(String key, String appName) {
        this.key = key;
        this.appName = appName;
        this.startTime = System.currentTimeMillis();
        this.elapsedTime = -1L;
        this.processState = 0;
    }

    public int getProcessState() {
        return this.processState;
    }

    public void error() {
        this.processState = 1;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public String getKey() {
        return this.key;
    }

    public String getAppName() {
        return this.appName;
    }

    public long getElapsedTime() {
        if (this.elapsedTime == -1L) {
            this.elapsedTime = System.currentTimeMillis() - this.startTime;
        }

        return this.elapsedTime;
    }

    public void stop() {
        if (null != this.appName && !"".equals(this.appName)) {
            CustomLogger.TpLogger.info(this.packagLogInfo());
        } else {
            CustomLogger.TpLogger.info(this.toString());
        }

    }

    @Override
    public String toString() {
        return LogFormatter.format(TP_LOG_TEMPLATE, new Object[]{CacheUtil.getNowTime(), this.key, this.processState, this.getElapsedTime()});
    }

    private String packagLogInfo() {
        return LogFormatter.format(AUTO_TP_LOG_TEMPLATE, new Object[]{CacheUtil.getNowTime(), this.key, this.appName, this.processState, this.getElapsedTime()});
    }

    static {
        TP_LOG_TEMPLATE = "{\"time\":\"{}\",\"key\":\"{}\",\"hostname\":\"" + CacheUtil.HOST_NAME + "\",\"processState\":" + "\"{}\",\"elapsedTime\":\"{}\"}";
        AUTO_TP_LOG_TEMPLATE = "{\"time\":\"{}\",\"key\":\"{}\",\"appName\":\"{}\",\"hostname\":\"" + CacheUtil.HOST_NAME + "\",\"processState\":" + "\"{}\",\"elapsedTime\":\"{}\"}";
    }
}
