package com.ca.fire.jvmMonitor.profiler.common;

import com.ca.fire.jvmMonitor.profiler.jvm.JvmInfoPicker;
import com.ca.fire.jvmMonitor.profiler.jvm.JvmInfoPickerFactory;
import com.ca.fire.jvmMonitor.profiler.util.CacheUtil;
import com.ca.fire.jvmMonitor.profiler.util.CustomLogger;

import java.util.TimerTask;

/**
 * Created on 2019/12/1
 */
public class JvmModule extends TimerTask {
    private String key;
    private static JvmInfoPicker localJvm = JvmInfoPickerFactory.create("Local");
    private static String instanceCode;
    private static String logType;

    public JvmModule(String key) {
        this.key = key;
    }

    @Override
    public void run() {
        try {
            CustomLogger.JVMLogger.info("{\"logtype\":\"" + logType + "\"" + ",\"key\":" + "\"" + this.key + "\"" + ",\"hostname\":" + "\"" + CacheUtil.HOST_NAME + "\"" + ",\"time\":" + "\"" + CacheUtil.getNowTime() + "\"" + ",\"datatype\":" + "\"" + "2" + "\"" + ",\"instancecode\":" + "\"" + instanceCode + "\"" + ",\"userdata\":" + localJvm.pickJvmRumtimeInfo() + "}");
        } catch (Throwable var2) {
        }

    }

    public static void jvmHandle(String jvmKey) {
        try {
            CustomLogger.JVMLogger.info("{\"logtype\":\"" + logType + "\"" + ",\"key\":" + "\"" + jvmKey + "\"" + ",\"hostname\":" + "\"" + CacheUtil.HOST_NAME + "\"" + ",\"time\":" + "\"" + CacheUtil.getNowTime() + "\"" + ",\"datatype\":" + "\"" + "1" + "\"" + ",\"instancecode\":" + "\"" + instanceCode + "\"" + ",\"userdata\":" + localJvm.pickJvmEnvironmentInfo() + "}");
        } catch (Throwable var2) {
        }

    }

    static {
        instanceCode = localJvm.getJvmInstanceCode();
        logType = "JVM";
    }
}
