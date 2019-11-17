package com.ca.fire.test.net;

import org.apache.commons.lang.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestIp {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();
        if (StringUtils.isNotBlank(hostAddress)) {
            String[] split = hostAddress.split("\\.");
            String s = split[split.length - 1];
            System.out.println(s);

        }
    }
}
