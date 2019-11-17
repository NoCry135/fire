package com.ca.fire.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(MyPropertiesUtil.class);

    public static String getProperty(String property, String name) {
        Properties properties = new Properties();
        ClassLoader classLoader = MyPropertiesUtil.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(property);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            logger.error("读取文件失败", e);
        }

        String property2 = properties.getProperty(name);

        return property2;
    }

}
