package com.ca.fire.test.jvm.monitor;

import com.sun.management.OperatingSystemMXBean;
import org.junit.Test;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.List;

public class    LocalJvmInfoPicker {

    public static void main(String[] args) {
        final List<GarbageCollectorMXBean> gcList = ManagementFactory.getGarbageCollectorMXBeans();
        if (gcList == null || gcList.isEmpty()) {
            return;
        } else {
            for (GarbageCollectorMXBean garbageCollectorMXBean : gcList) {
                System.out.println(garbageCollectorMXBean.getName() + "#  time: " + garbageCollectorMXBean.getCollectionTime() + ", count: " + garbageCollectorMXBean.getCollectionCount());
            }
        }
        String property = System.getProperties().getProperty("os.arch");
        String property1 = System.getProperties().getProperty("os.name");
        String property4 = System.getProperties().getProperty("sun.arch.data.model");
        String property3 = System.getProperties().getProperty("java.library.path");
        String property2 = System.getProperties().getProperty("java.version");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String format = sdf.format(ManagementFactory.getRuntimeMXBean().getStartTime());

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean);


        long committed = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getCommitted();
        System.out.println(committed);




    }

    @Test
    public void getCpu() {
        final OperatingSystemMXBean osbean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        final long uptimeNow = ManagementFactory.getRuntimeMXBean().getUptime();
        final long processCpuTimeNow = osbean.getProcessCpuTime();
        String cpu = "0.0";
//        if (this.uptime > 0L && this.processCpuTime > 0L) {
//            final long l2 = uptimeNow - this.uptime;
//            final long l3 = processCpuTimeNow - this.processCpuTime;
//            if (l2 > 0L) {
//                final float cpuValue = Math.min(99.0f, l3 / (l2 * 10000.0f * osbean.getAvailableProcessors()));
//                final DecimalFormat df = new DecimalFormat("##0.0##");
//                cpu = df.format(cpuValue);
//            }
//        }
//        this.uptime = uptimeNow;
//        this.processCpuTime = processCpuTimeNow;

    }
    public String getOSArch() {
        return System.getProperties().getProperty("os.arch");
    }

    public String getOSName() {
        return System.getProperties().getProperty("os.name");
    }

    public String getSystemModel() {
        return System.getProperties().getProperty("sun.arch.data.model");
    }

    public String getLibPath() {
        return System.getProperties().getProperty("java.library.path");
    }

    public String getJREVersion() {
        return System.getProperties().getProperty("java.version");
    }

    public String getStartTime() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(ManagementFactory.getRuntimeMXBean().getStartTime());
    }

    public String getClassPath() {
        return ManagementFactory.getRuntimeMXBean().getClassPath();
    }

    public String getBootClassPath() {
        return ManagementFactory.getRuntimeMXBean().getBootClassPath();
    }

    public long getPeakThreadCount() {
        return ManagementFactory.getThreadMXBean().getPeakThreadCount();
    }

    public long getThreadCount() {
        return ManagementFactory.getThreadMXBean().getThreadCount();
    }

    public long getDaemonThreadCount() {
        return ManagementFactory.getThreadMXBean().getDaemonThreadCount();
    }

    public int getPid() {
        final String name = ManagementFactory.getRuntimeMXBean().getName();
        try {
            return Integer.parseInt(name.substring(0, name.indexOf(64)));
        }
        catch (Exception e) {
            return -1;
        }
    }

    public int getAvailableProcessors() {
        return ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
    }

    public String getJREVendor() {
        return System.getProperties().getProperty("java.vm.vendor");
    }
}
