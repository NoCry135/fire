//package com.ca.fire.jvmMonitor.profiler.proxy;
//
//import com.ca.fire.jvmMonitor.profiler.CallerInfo;
//import com.ca.fire.jvmMonitor.profiler.ProfilerImpl;
//
//import java.util.Map;
//
//public class Profiler {
//
//    public static CallerInfo registerInfo(final String key) {
//        CallerInfo callerInfo = null;
//        if (key != null && !"".equals(key.trim())) {
//            callerInfo = ProfilerImpl.scopeStart(key.trim(), true, false);
//        }
//        return callerInfo;
//    }
//
//    public static CallerInfo registerInfo(final String key, final boolean enableHeartbeat, final boolean enableTP) {
//        CallerInfo callerInfo = null;
//        if (key != null && !"".equals(key.trim())) {
//            callerInfo = ProfilerImpl.scopeStart(key.trim(), enableHeartbeat, enableTP);
//        }
//        return callerInfo;
//    }
//
//    public static void registerInfoEnd(final CallerInfo callerInfo) {
//        ProfilerImpl.scopeEnd(callerInfo);
//    }
//
//    public static void businessAlarm(final String key, final String detail) {
//        if (key != null && !"".equals(key.trim()) && detail != null && !"".equals(detail.trim())) {
//            ProfilerImpl.registerBusiness(key.trim(), 0, 0, detail.trim());
//        }
//    }
//
//    public static void businessAlarm(final String key, final long time, final String detail) {
//        if (key != null && !"".equals(key.trim()) && detail != null && !"".equals(detail.trim())) {
//            ProfilerImpl.registerBusiness(key.trim(), 0, 0, detail.trim());
//        }
//    }
//
//    public static void businessAlarm(final String key, final String detail, final String rtxList, final String mailList, final String smsList) {
//        if (key != null && !"".equals(key.trim()) && detail != null && !"".equals(detail.trim())) {
//            ProfilerImpl.registerBusiness(key.trim(), 5, 0, detail.trim(), rtxList, mailList, smsList);
//        }
//    }
//
//    public static void businessAlarm(final String key, final long time, final String detail, final String rtxList, final String mailList, final String smsList) {
//        if (key != null && !"".equals(key.trim()) && detail != null && !"".equals(detail.trim())) {
//            ProfilerImpl.registerBusiness(key.trim(), 5, 0, detail.trim(), rtxList, mailList, smsList);
//        }
//    }
//
//    public static void InitHeartBeats(final String key) {
//        if (key != null && !"".equals(key.trim())) {
//            ProfilerImpl.scopeAlive(key.trim());
//        }
//    }
//
//    public static void functionError(final CallerInfo callerInfo) {
//        ProfilerImpl.scopeFunctionError(callerInfo);
//    }
//
//    public static void valueAccumulate(final String key, final Number bValue) {
//        if (key != null && !"".equals(key.trim()) && bValue != null) {
//            ProfilerImpl.registerBizData(key.trim(), 1, bValue);
//        }
//    }
//
//    public static void countAccumulate(final String key) {
//        if (key != null && !"".equals(key.trim())) {
//            ProfilerImpl.registerBizData(key.trim(), 2, 1);
//        }
//    }
//
//    public static void sourceDataByNum(final String key, final Map<String, Number> dataMap) {
//        if (key != null && !"".equals(key.trim()) && dataMap != null && !dataMap.isEmpty()) {
//            ProfilerImpl.registerBizData(key.trim(), 3, dataMap);
//        }
//    }
//
//    public static void sourceDataByStr(final String key, final Map<String, String> dataMap) {
//        if (key != null && !"".equals(key.trim()) && dataMap != null && !dataMap.isEmpty()) {
//            ProfilerImpl.registerBizData(key.trim(), 3, dataMap);
//        }
//    }
//
//    public static void registerJVMInfo(final String key) {
//        if (key != null && !"".equals(key.trim())) {
//            ProfilerImpl.registerJvmData(key.trim());
//        }
//    }
//
//    public static void bizNode(String nodeID, final Map<String, String> data) {
//        try {
//            if (nodeID != null && !"".equals(nodeID = nodeID.trim()) && data != null && !data.isEmpty()) {
//                ProfilerImpl.bizNode(nodeID, data);
//            }
//        } catch (Throwable t) {
//        }
//    }
//
//    public static void bizNode(String nodeID, String data) {
//        try {
//            if (nodeID != null && !"".equals(nodeID = nodeID.trim()) && data != null && !"".equals(data = data.trim())) {
//                ProfilerImpl.bizNode(nodeID, data);
//            }
//        } catch (Throwable t) {
//        }
//    }
//
//    public static void log(String type, String data) {
//        try {
//            if (type != null && !"".equals(type = type.trim()) && data != null && !"".equals(data = data.trim())) {
//                ProfilerImpl.log(type, data);
//            }
//        } catch (Throwable t) {
//        }
//    }
//
//    public static void log(String type, final Map<String, String> data) {
//        try {
//            if (type != null && !"".equals(type = type.trim()) && data != null && !data.isEmpty()) {
//                ProfilerImpl.log(type, data);
//            }
//        } catch (Throwable t) {
//        }
//    }
//}
