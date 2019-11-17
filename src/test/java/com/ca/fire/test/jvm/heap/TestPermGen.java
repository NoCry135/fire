package com.ca.fire.test.jvm.heap;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestPermGen {

    private static List<Object> insList = new ArrayList<Object>();

    public static void main(String[] args) throws Exception {

        permLeak();
    }

    private static void permLeak() throws Exception {
        for (int i = 0; i < 1000; i++) {
            URL[] urls = getURLS();
            URLClassLoader urlClassloader = new URLClassLoader(urls, null);
            Class<?> logfClass = Class.forName("org.apache.commons.collections4.CollectionUtils", true, urlClassloader);
            Method isEmpty = logfClass.getMethod("isEmpty", Collection.class);
            Object result = isEmpty.invoke(logfClass, new ArrayList<String>());
            insList.add(result);
            System.out.println(i + ": " + result);

        }
    }

    private static URL[] getURLS() throws MalformedURLException {
        File libDir = new File("D:\\Applications\\maven\\Rep\\dependRepository\\org\\apache\\commons\\commons-collections4\\4.0");
        File[] subFiles = libDir.listFiles();
        int count = subFiles.length;
        URL[] urls = new URL[count];
        for (int i = 0; i < count; i++) {
            urls[i] = subFiles[i].toURI().toURL();
        }
        return urls;
    }


}
