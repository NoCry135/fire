package com.ca.fire.jvmMonitor.profiler.common;

import com.ca.fire.jvmMonitor.profiler.util.CacheUtil;
import com.ca.fire.jvmMonitor.profiler.util.CustomLogger;

import java.util.TimerTask;

public class AliveModule extends TimerTask {

    private String key;

    public AliveModule(final String key) {
        this.key = key;
    }

    @Override
    public void run() {
        try {
            CustomLogger.AliveLogger.info("{\"key\":\"" + this.key + "\"" + ",\"hostname\":" + "\"" + CacheUtil.HOST_NAME + "\"" + ",\"time\":" + "\"" + CacheUtil.getNowTime() + "\"}");
        } catch (Exception ex) {
        }
    }
}
