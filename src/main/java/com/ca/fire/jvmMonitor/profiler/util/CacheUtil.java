package com.ca.fire.jvmMonitor.profiler.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CacheUtil {

    private static final Pattern IP_PATTERN;
    private static final String STR_HOST_ERROR_DETECTED = "** HOST ERROR DETECTED **";
    private static final String STR_IP_ERROR_DETECTED = "** IP ERROR DETECTED **";
    private static final String LOG_TIME_FORMAT = "yyyyMMddHHmmssSSS";
    public static final long UPDATETIME = 43200000L;
    public static final int ALIVETIME = 20000;
    public static final int JVMTIME_R = 10000;
    public static final int JVMTIME_E = 14400000;
    public static Boolean SYSTEM_HEART_INIT;
    public static Boolean JVM_MONITOR_INIT;
    public static final String QUOTATION = "\"";
    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String EXTENSIVE = "1";
    public static final String NONEXTENSIVE = "0";
    public static final Map<String, Long> FUNC_HB;
    public static final String HOST_NAME;
    public static final String HOST_IP;

    private static String getHostName() {
        String host = "** HOST ERROR DETECTED **";
        try {
            try {
                final InetAddress localAddress = InetAddress.getLocalHost();
                host = localAddress.getHostName();
            } catch (Throwable e) {
                final InetAddress localAddress2 = getLocalAddress();
                if (localAddress2 != null) {
                    host = localAddress2.getHostName();
                } else {
                    host = "** HOST ERROR DETECTED **";
                }
            }
        } catch (Throwable t) {
        }
        return host;
    }

    public static String getHostIP() {
        String ip = "** IP ERROR DETECTED **";
        try {
            if (getLocalAddress() != null) {
                ip = getLocalAddress().getHostAddress();
            } else {
                ip = "** IP ERROR DETECTED **";
            }
        } catch (Throwable t) {
        }
        return ip;
    }

    public static String getNowTime() {
        String nowTime = null;
        try {
            final Date rightNow = new Date();
            final DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            nowTime = format1.format(rightNow);
        } catch (Exception e) {
            nowTime = "";
        }
        return nowTime;
    }

    public static String changeLongToDate(final long time) {
        String nowTime = null;
        try {
            final Date date = new Date(time);
            final DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            nowTime = format1.format(date);
        } catch (Exception e) {
            nowTime = "";
        }
        return nowTime;
    }

    public static String getLocalIP() {
        final InetAddress address = getLocalAddress();
        return (address == null) ? null : address.getHostAddress();
    }

    private static InetAddress getLocalAddress() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable t) {
        }
        try {
            final Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        final NetworkInterface network = interfaces.nextElement();
                        final Enumeration<InetAddress> addresses = network.getInetAddresses();
                        if (addresses == null) {
                            continue;
                        }
                        while (addresses.hasMoreElements()) {
                            try {
                                final InetAddress address = addresses.nextElement();
                                if (isValidAddress(address)) {
                                    return address;
                                }
                                continue;
                            } catch (Throwable e) {
                            }
                        }
                    } catch (Throwable e2) {
                    }
                }
            }
        } catch (Throwable t2) {
        }
        return localAddress;
    }

    private static boolean isValidAddress(final InetAddress address) {
        if (address == null || address.isLoopbackAddress()) {
            return false;
        }
        final String ip = address.getHostAddress();
        return ip != null && !"0.0.0.0".equals(ip) && !"127.0.0.1".equals(ip) && CacheUtil.IP_PATTERN.matcher(ip).matches();
    }

    static {
        IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");
        CacheUtil.SYSTEM_HEART_INIT = false;
        CacheUtil.JVM_MONITOR_INIT = false;
        FUNC_HB = new HashMap<String, Long>();
        HOST_NAME = getHostName();
        HOST_IP = getHostIP();
    }
}
