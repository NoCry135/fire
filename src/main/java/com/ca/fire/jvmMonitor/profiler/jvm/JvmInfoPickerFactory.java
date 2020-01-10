package com.ca.fire.jvmMonitor.profiler.jvm;

/**
 * Created on 2019/12/1
 */
public class JvmInfoPickerFactory {
    public static final String PICKER_TYPE = "Local";

    public static JvmInfoPicker create(String type) {
        return LocalJvmInfoPicker.getInstance();
    }
}
