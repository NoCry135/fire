//package com.ca.fire.jvmMonitor.profiler;
//
//import com.sun.javaws.CacheUtil;
//import org.apache.zookeeper.server.LogFormatter;
//
//public class CallerInfo {
//    public static final int STATE_TRUE = 0;
//    public static final int STATE_FALSE = 1;
//    private static final String TP_LOG_TEMPLATE;
//    private long startTime;
//    private String key;
//    private int processState;
//    private long elapsedTime;
//
//    public CallerInfo(final String key) {
//        this.key = key;
//        this.startTime = System.currentTimeMillis();
//        this.elapsedTime = -1L;
//        this.processState = 0;
//    }
//
//    public int getProcessState() {
//        return this.processState;
//    }
//
//    public void error() {
//        this.processState = 1;
//    }
//
//    public long getStartTime() {
//        return this.startTime;
//    }
//
//    public String getKey() {
//        return this.key;
//    }
//
//    public long getElapsedTime() {
//        if (this.elapsedTime == -1L) {
//            this.elapsedTime = System.currentTimeMillis() - this.startTime;
//        }
//        return this.elapsedTime;
//    }
//
//    public void stop() {
//        CustomLogger.TpLogger.info(this.toString());
//    }
//
//    @Override
//    public String toString() {
//        return LogFormatter.format(CallerInfo.TP_LOG_TEMPLATE, CacheUtil.getNowTime(), this.key, this.processState, this.getElapsedTime());
//    }
//
//    static {
//        TP_LOG_TEMPLATE = "{\"time\":\"{}\",\"key\":\"{}\",\"hostname\":\"" + CacheUtil.HOST_NAME + "\",\"processState\":" + "\"{}\",\"elapsedTime\":\"{}\"}";
//    }
//}
