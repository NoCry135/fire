package com.ca.fire.test.jvm;

import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestMemory {
    private Logger logger = LoggerFactory.getLogger(TestMemory.class);

    private static List<Object[]> list = new ArrayList<>();

    public static void main(String[] args) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        for (int i = 0; i < 100000; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test(i);
        }
    }

    public static void test(Integer num) {
        System.out.println("excute:" + num);
        Object[] obj = new Object[num + 100];
        for (int i = 0; i < num + 100; i++) {
            obj[i] = i;

        }
        list.add(obj);
    }

    private void printSystemInfo() {
        try {
            OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            List<MemoryPoolMXBean> mxb = ManagementFactory.getMemoryPoolMXBeans();
            int mb = 1048576;
//            logger.info("System Info: " + StrUtil.getSystemInfo());
            logger.info("OS Name: " + System.getProperty("os.name"));
            logger.info("JVM Name: " + runtime.getVmName());
            logger.info("JVM Version: " + System.getProperty("java.version"));
            logger.info("Available Processors: " + Runtime.getRuntime().availableProcessors());
            logger.info("Physical Memory: {Total:" + osmxb.getTotalPhysicalMemorySize() / mb + "M, Free:" + osmxb.getFreePhysicalMemorySize() / mb + "M, Max:" + (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / mb + "M}");
            logger.info("Runtime Memory: {Total:" + Runtime.getRuntime().totalMemory() / mb + "M, Free:" + Runtime.getRuntime().freeMemory() / mb + "M, Max:" + Runtime.getRuntime().maxMemory() / mb + "M}");
            for (MemoryPoolMXBean memoryPoolMXBean : mxb) {
                logger.info(memoryPoolMXBean.getName() + ": {Init:" + memoryPoolMXBean.getUsage().getInit() / mb + "M, Committed:" + memoryPoolMXBean.getUsage().getCommitted() / mb + "M, Used:" + memoryPoolMXBean.getUsage().getUsed() / mb + "M, Max:" + memoryPoolMXBean.getUsage().getMax() / mb + "M} " + Arrays.asList(memoryPoolMXBean.getMemoryManagerNames()));
            }
        } catch (Exception e) {
            int mb;
            logger.error("00", e);
        }
    }
}
