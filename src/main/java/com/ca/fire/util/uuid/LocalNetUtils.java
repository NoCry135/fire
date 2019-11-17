package com.ca.fire.util.uuid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Locale;

/**
 */
public final class LocalNetUtils {
    private static Logger logger = LoggerFactory.getLogger(LocalNetUtils.class);

    public static String getHostName() {
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (Exception e) {
            logger.error("getLocalHost error!", e);
            return null;
        }
        return inetAddress.getHostName();
    }

    public static String getMacAddress() {
        String mac;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    mac = pickInetAddress(inetAddress, networkInterface);
                    if (mac != null) {
                        return mac;
                    }
                }
            }
        } catch (SocketException e) {
            logger.error("getMacAddress error!", e);
        }
        return null;
    }

    private static String pickInetAddress(InetAddress inetAddress, NetworkInterface ni) throws SocketException {
        String name = ni.getDisplayName();
        if (name.contains("Adapter") || name.contains("Virtual") || name.contains("VMnet") || name.contains("#")) {
            return null;
        }
        if (ni.isVirtual() || !ni.isUp() || !ni.supportsMulticast()) {
            return null;
        }
        if (inetAddress.isSiteLocalAddress()) {
            Formatter formatter = new Formatter();
            String mac = null;
            byte[] macBuf = ni.getHardwareAddress();
            for (int i = 0; i < macBuf.length; i++) {
                mac = formatter.format(Locale.getDefault(), "%02X%s", macBuf[i], (i < macBuf.length - 1) ? "-" : "").toString();
            }
            formatter.close();
            return mac;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getMacAddress());
        System.out.println(getHostName());
    }

}
