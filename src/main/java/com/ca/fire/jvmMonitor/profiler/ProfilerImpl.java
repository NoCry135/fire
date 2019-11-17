//package com.ca.fire.jvmMonitor.profiler;
//
//import com.ca.fire.jvmMonitor.profiler.common.AliveModule;
//import com.ca.fire.jvmMonitor.profiler.util.CacheUtil;
//
//public class ProfilerImpl {
//    private static final TPCounter tpCounter;
//
//    public static CallerInfo scopeStart(final String key, final boolean enableHeart, final boolean enableTP) {
//        CallerInfo callerInfo = null;
//        try {
//            if (enableTP) {
//                callerInfo = new CallerInfo(key);
//            }
//            if (enableHeart) {
//                if (!CacheUtil.FUNC_HB.containsKey(key)) {
//                    synchronized (CacheUtil.FUNC_HB) {
//                        if (!CacheUtil.FUNC_HB.containsKey(key)) {
//                            CacheUtil.FUNC_HB.put(key, System.currentTimeMillis());
//                            CustomLogger.AliveLogger.info("{\"key\":\"" + key + "\"" + ",\"hostname\":" + "\"" + CacheUtil.HOST_NAME + "\"" + ",\"time\":" + "\"" + CacheUtil.getNowTime() + "\"}");
//                        }
//                    }
//                } else {
//                    final Long hbPoint = CacheUtil.FUNC_HB.get(key);
//                    if (System.currentTimeMillis() - hbPoint >= 20000L) {
//                        synchronized (CacheUtil.FUNC_HB) {
//                            if (System.currentTimeMillis() - CacheUtil.FUNC_HB.get(key) >= 20000L) {
//                                CacheUtil.FUNC_HB.put(key, System.currentTimeMillis());
//                                CustomLogger.AliveLogger.info("{\"key\":\"" + key + "\"" + ",\"hostname\":" + "\"" + CacheUtil.HOST_NAME + "\"" + ",\"time\":" + "\"" + CacheUtil.getNowTime() + "\"}");
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Throwable t) {
//        }
//        return callerInfo;
//    }
//
//    public static CallerInfo scopeStart(final String key) {
//        return scopeStart(key, false, true);
//    }
//
//    public static void scopeEnd(final CallerInfo callerInfo) {
//        try {
//            if (callerInfo != null) {
//                if (callerInfo.getProcessState() == 1) {
//                    callerInfo.stop();
//                } else {
//                    final long elapsedTime = callerInfo.getElapsedTime();
//                    if (elapsedTime >= 400L) {
//                        callerInfo.stop();
//                    } else {
//                        ProfilerImpl.tpCounter.count(callerInfo, elapsedTime);
//                    }
//                }
//            }
//        } catch (Throwable t) {
//        }
//    }
//
//    public static synchronized void scopeAlive(final String key) {
//        try {
//            if (!CacheUtil.SYSTEM_HEART_INIT) {
//                final Timer timer = new Timer("UMP-AliveThread", true);
//                timer.scheduleAtFixedRate(new AliveModule(key), 1000L, 20000L);
//                CacheUtil.SYSTEM_HEART_INIT = true;
//            }
//        } catch (Throwable t) {
//        }
//    }
//
//    public static void registerBusiness(final String key, final int type, final int value, final String detail) {
//        try {
//            BusinessModule.businessHandle(key, type, value, detail);
//        } catch (Throwable t) {
//        }
//    }
//
//    public static void registerBusiness(final String key, final int type, final int value, final String detail, final String rtxList, final String mailList, final String smsList) {
//        try {
//            BusinessModule.businessHandle(key, type, value, detail, rtxList, mailList, smsList);
//        } catch (Throwable t) {
//        }
//    }
//
//    public static void scopeFunctionError(final CallerInfo callerInfo) {
//        try {
//            if (callerInfo != null) {
//                callerInfo.error();
//            }
//        } catch (Throwable t) {
//        }
//    }
//
//    public static void registerBizData(final String key, final int type, final Number value) {
//        try {
//            BizModule.bizHandle(key, type, value);
//        } catch (Throwable t) {
//        }
//    }
//
//    public static void registerBizData(final String key, final int type, final Map<String, ?> dataMap) {
//        try {
//            BizModule.bizHandle(key, type, dataMap);
//        } catch (Throwable t) {
//        }
//    }
//
//    public static synchronized void registerJvmData(final String key) {
//        try {
//            if (!CacheUtil.JVM_MONITOR_INIT) {
//                final Timer timer = new Timer("UMP-CollectJvmInfoThread", true);
//                timer.scheduleAtFixedRate(new JvmModule(key), 1000L, 10000L);
//                timer.scheduleAtFixedRate(new TimerTask() {
//                    @Override
//                    public void run() {
//                        JvmModule.jvmHandle(key);
//                    }
//                }, 0L, 14400000L);
//                CacheUtil.JVM_MONITOR_INIT = true;
//            }
//        } catch (Throwable t) {
//        }
//    }
//
//    public static void bizNode(final String nodeID, final Map<String, String> data) {
//        BizModule.bizNode(nodeID, data);
//    }
//
//    public static void bizNode(final String nodeID, final String data) {
//        BizModule.bizNode(nodeID, data);
//    }
//
//    public static void log(final String type, final String data) {
//        CommonModule.log(type, data);
//    }
//
//    public static void log(final String type, final Map<String, String> data) {
//        CommonModule.log(type, data);
//    }
//
//    static {
//        tpCounter = TPCounter.getInstance();
//    }
//}
