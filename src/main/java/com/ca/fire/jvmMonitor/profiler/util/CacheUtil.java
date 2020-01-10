package com.ca.fire.jvmMonitor.profiler.util;

import com.ca.fire.jvmMonitor.profiler.jvm.LocalJvmInfoPicker;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class CacheUtil {

    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");
    private static final String STR_HOST_ERROR_DETECTED = "** HOST ERROR DETECTED **";
    private static final String STR_IP_ERROR_DETECTED = "** IP ERROR DETECTED **";
    private static final String LOG_TIME_FORMAT = "yyyyMMddHHmmssSSS";
    public static final long UPDATETIME = 43200000L;
    public static final int ALIVETIME = 20000;
    public static final int JVMTIME_R = 10000;
    public static final int JVMTIME_E = 14400000;
    public static Boolean SYSTEM_HEART_INIT = Boolean.valueOf(false);
    public static Boolean JVM_MONITOR_INIT = Boolean.valueOf(false);
    public static final String QUOTATION = "\"";
    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String EXTENSIVE = "1";
    public static final String NONEXTENSIVE = "0";
    public static final Map<String, Long> FUNC_HB = new HashMap();
    private static final LocalJvmInfoPicker jvmInfoPicker = LocalJvmInfoPicker.getInstance();
    public static final String HOST_NAME = jvmInfoPicker.getHostName();
    public static final int cpus = jvmInfoPicker.getAvailableProcessors();
    public static final String HOST_IP = getHostIP();
    public static final String LINE_SEP = System.getProperty("line.separator");
    public static final byte[] LINE_SEP_BYTES = LINE_SEP.getBytes();
    public static final String APP_NAME = getAppName();
    public static final byte[] APP_NAME_BYTES = APP_NAME.getBytes();
    public static final String UMP_PREFFIX = getPreffix();
    private static final String DEFAULT_PATH = "/export/home/tomcat/UMP-Monitor";
    public static boolean is_jdos = false;

    private static String getPreffix() {
        String preffix;
        if (((preffix = System.getProperty("umpPreffix")) != null) && (!preffix.equals(""))) {
            return preffix.charAt(preffix.length() - 1) == '/' ? preffix.substring(0, preffix.length() - 1) : preffix;
        }
        Properties conf = new Properties();
        Properties props = null;
        InputStream is = null;
        try {
            is = CacheUtil.class.getResourceAsStream("/config.properties");
            if (is != null) {
                conf.load(is);
                preffix = conf.getProperty("jiankonglogPath", "/export/home/tomcat/UMP-Monitor");
                if (preffix.equals("")) {
                    preffix = "/export/home/tomcat/UMP-Monitor";
                }
            } else {
                preffix = "/export/home/tomcat/UMP-Monitor";
            }
            return preffix;
        } catch (Throwable localThrowable4) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Throwable localThrowable3) {
                }
            }
        }
        return "/export/home/tomcat/UMP-Monitor";
    }

    private static String getAppName() {
        String appName;
        if (((appName = System.getenv("def_app_name")) != null) && (!appName.equals(""))) {
            return appName;
        }
        if (((appName = System.getenv("deploy_app_name")) != null) && (!appName.equals(""))) {
            is_jdos = true;
            return "jdos_" + appName;
        }
        if (((appName = System.getProperty("appName")) != null) && (!appName.equals(""))) {
            return appName;
        }
        System.out.println("UMP Daemon can't get appName from your environment!!! This issue is very serious!!! please read wiki!");

        return "unknown";
    }

    private static String getHostIP() {
        String ip = "** IP ERROR DETECTED **";
        try {
            if (getLocalAddress() != null) {
                ip = getLocalAddress().getHostAddress();
            } else {
                ip = "** IP ERROR DETECTED **";
            }
        } catch (Throwable localThrowable) {
        }
        return ip;
    }

    public static String getNowTime() {
        String nowTime = null;
        try {
            Date rightNow = new Date();
            TimeZone localTimeZone = TimeZone.getTimeZone("GMT+8");
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            df.setTimeZone(localTimeZone);
            nowTime = df.format(rightNow);
        } catch (Exception e) {
            nowTime = "";
        }
        return nowTime;
    }

    public static String changeLongToDate(long time) {
        String nowTime = null;
        try {
            Date date = new Date(time);
            TimeZone localTimeZone = TimeZone.getTimeZone("GMT+8");
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            df.setTimeZone(localTimeZone);
            nowTime = df.format(date);
        } catch (Exception e) {
            nowTime = "";
        }
        return nowTime;
    }

    public static String getLocalIP() {
        InetAddress address = getLocalAddress();
        return address == null ? null : address.getHostAddress();
    }

    private static InetAddress getLocalAddress() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable localThrowable) {
        }
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = (NetworkInterface) interfaces.nextElement();
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        if (addresses != null) {
                            while (addresses.hasMoreElements()) {
                                try {
                                    InetAddress address = (InetAddress) addresses.nextElement();
                                    if (isValidAddress(address)) {
                                        return address;
                                    }
                                } catch (Throwable localThrowable1) {
                                }
                            }
                        }
                    } catch (Throwable localThrowable2) {
                    }
                }
            }
        } catch (Throwable localThrowable3) {
        }
        return localAddress;
    }

    private static boolean isValidAddress(InetAddress address) {
        if ((address == null) || (address.isLoopbackAddress())) {
            return false;
        }
        String ip = address.getHostAddress();

        return (ip != null) && (!"0.0.0.0".equals(ip)) && (!"127.0.0.1".equals(ip)) && (IP_PATTERN.matcher(ip).matches());
    }
}
