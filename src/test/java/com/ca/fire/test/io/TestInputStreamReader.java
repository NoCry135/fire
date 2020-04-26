package com.ca.fire.test.io;

import java.io.*;

public class TestInputStreamReader {
    public static void main(String[] args) throws Exception {
        test1("info1.txt");
        test("info1.txt");
        test("info1.txt", "utf-8");
    }

    //读取一个utf-8编码的文件，使用平台默认字符集GBK解码
    public static void test1(String filePath) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    //读取一个utf-8编码的文件，使用平台默认字符集GBK解码
    public static void test(String filePath) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    //使用指定字符集，来读取文件
    public static void test(String filePath, String charSetName) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), charSetName));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

}
