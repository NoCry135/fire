package com.ca.fire.jvmMonitor.profiler.jvm;

/**
 * Created on 2019/12/1
 */
public interface JvmInfoPicker {
    String pickJvmEnvironmentInfo();

    String pickJvmRumtimeInfo();

    String getJvmInstanceCode();

    String getHostName();
}
