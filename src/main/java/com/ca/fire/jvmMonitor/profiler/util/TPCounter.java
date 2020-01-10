package com.ca.fire.jvmMonitor.profiler.util;

import com.ca.fire.jvmMonitor.profiler.CallerInfo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 2019/12/1
 */
public class TPCounter {
    public static final long MAX_TP_COUNT_ELAPSED_TIME = 400L;
    private static final long COUNT_TP_PERIOD = 5000L;
    private static final long WRITE_TP_LOG_DELAY = 1000L;
    private static final String KEY_STORE_KEY_SPLIT_STR = "###";
    private static final String TIME_COUNT_SEP = ",";
    private static final TPCounter counter = new TPCounter();
    private ConcurrentHashMap<String, ConcurrentHashMultiSet<Integer>> tpCountMap = new ConcurrentHashMap();
    private static final String TP_LOG_TEMPLATE;
    private static final String AUTO_TP_LOG_TEMPLATE;

    private TPCounter() {
        long lastTimePoint = (new Date()).getTime() / 5000L * 5000L;
        Date firstWriteTime = new Date(lastTimePoint + 5000L);
        Timer writeTPLogTimer = new Timer("UMP-WriteTPLogThread", true);
        writeTPLogTimer.scheduleAtFixedRate(new TPCounter.WriteTPLogTask(), firstWriteTime, 5000L);
    }

    public static TPCounter getInstance() {
        return counter;
    }

    public void count(CallerInfo callerInfo, long elapsedTime) {
        String countMapKey = this.getCountMapKey(callerInfo);
        ConcurrentHashMultiSet<Integer> elapsedTimeCounter = (ConcurrentHashMultiSet) this.tpCountMap.get(countMapKey);
        if (elapsedTimeCounter == null) {
            ConcurrentHashMultiSet<Integer> newElapsedTimeCounter = new ConcurrentHashMultiSet();
            elapsedTimeCounter = (ConcurrentHashMultiSet) this.tpCountMap.putIfAbsent(countMapKey, newElapsedTimeCounter);
            if (elapsedTimeCounter == null) {
                elapsedTimeCounter = newElapsedTimeCounter;
            }
        }

        elapsedTimeCounter.add((int) elapsedTime);
    }

    private String getCountMapKey(CallerInfo callerInfo) {
        return null != callerInfo.getAppName() && !"".equals(callerInfo.getAppName()) ? callerInfo.getKey() + "###" + callerInfo.getAppName() + "###" + System.currentTimeMillis() / 5000L * 5000L : callerInfo.getKey() + "###" + System.currentTimeMillis() / 5000L * 5000L;
    }

    static {
        TP_LOG_TEMPLATE = "{\"time\":\"{}\",\"key\":\"{}\",\"hostname\":\"" + CacheUtil.HOST_NAME + "\",\"processState\":" + "\"" + 0 + "\",\"elapsedTime\":\"{}\",\"count\":\"{}\"}";
        AUTO_TP_LOG_TEMPLATE = "{\"time\":\"{}\",\"key\":\"{}\",\"appName\":\"{}\",\"hostname\":\"" + CacheUtil.HOST_NAME + "\",\"processState\":" + "\"" + 0 + "\",\"elapsedTime\":\"{}\",\"count\":\"{}\"}";
    }

    private class WriteTPLogTask extends TimerTask {
        private WriteTPLogTask() {
        }

        @Override
        public void run() {
            try {
                Map<String, ConcurrentHashMultiSet<Integer>> writeCountMap = TPCounter.this.tpCountMap;
                TPCounter.this.tpCountMap = new ConcurrentHashMap();
                this.writeTPLog(writeCountMap);
            } catch (Throwable var2) {
            }

        }

        private void writeTPLog(Map<String, ConcurrentHashMultiSet<Integer>> writeCountMap) throws InterruptedException {
            if (writeCountMap != null) {
                Thread.sleep(1000L);
                StringBuilder logs = new StringBuilder(1024);
                Iterator i$x = writeCountMap.entrySet().iterator();

                while (true) {
                    while (i$x.hasNext()) {
                        Map.Entry<String, ConcurrentHashMultiSet<Integer>> entry = (Map.Entry) i$x.next();
                        String[] keyTime = ((String) entry.getKey()).split("###");
                        String key;
                        String appName;
                        StringBuilder elapsedTimes;
                        Integer elapsedTimex;
                        if (keyTime != null && keyTime.length == 2) {
                            key = keyTime[0].trim();
                            appName = CacheUtil.changeLongToDate(Long.valueOf(keyTime[1].trim()));
                            ConcurrentHashMultiSet<Integer> elapsedTimeCounter = (ConcurrentHashMultiSet) entry.getValue();
                            boolean needSetSep = false;
                            StringBuilder elapsedTimesx = new StringBuilder(1024);
                            elapsedTimes = new StringBuilder(1024);
                            Iterator i$xx = elapsedTimeCounter.elementSet().iterator();

                            while (i$xx.hasNext()) {
                                Integer elapsedTime = (Integer) i$xx.next();
                                if (needSetSep) {
                                    elapsedTimesx.append(",");
                                    elapsedTimes.append(",");
                                } else {
                                    needSetSep = true;
                                }

                                elapsedTimex = elapsedTimeCounter.count(elapsedTime);
                                elapsedTimesx.append(elapsedTime);
                                elapsedTimes.append(elapsedTimex);
                            }

                            if (elapsedTimesx.length() > 0) {
                                String log = LogFormatter.format(TPCounter.TP_LOG_TEMPLATE, new Object[]{appName, key, elapsedTimesx.toString(), elapsedTimes.toString()});
                                logs.append(log);
                                CustomLogger.TpLogger.info(logs.toString());
                                logs.setLength(0);
                            }
                        } else if (keyTime != null && keyTime.length == 3) {
                            key = keyTime[0].trim();
                            appName = keyTime[1].trim();
                            String time = CacheUtil.changeLongToDate(Long.valueOf(keyTime[2].trim()));
                            ConcurrentHashMultiSet<Integer> elapsedTimeCounterx = (ConcurrentHashMultiSet) entry.getValue();
                            boolean needSetSepx = false;
                            elapsedTimes = new StringBuilder(1024);
                            StringBuilder counts = new StringBuilder(1024);
                            Iterator i$ = elapsedTimeCounterx.elementSet().iterator();

                            while (i$.hasNext()) {
                                elapsedTimex = (Integer) i$.next();
                                if (needSetSepx) {
                                    elapsedTimes.append(",");
                                    counts.append(",");
                                } else {
                                    needSetSepx = true;
                                }

                                Integer count = elapsedTimeCounterx.count(elapsedTimex);
                                elapsedTimes.append(elapsedTimex);
                                counts.append(count);
                            }

                            if (elapsedTimes.length() > 0) {
                                String logx = LogFormatter.format(TPCounter.AUTO_TP_LOG_TEMPLATE, new Object[]{time, key, appName, elapsedTimes.toString(), counts.toString()});
                                logs.append(logx);
                                CustomLogger.TpLogger.info(logs.toString());
                                logs.setLength(0);
                            }
                        }
                    }

                    return;
                }
            }
        }
    }
}
