package com.ca.fire.test.io;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;

public class TestOutputStreamWriter {

    @Test
    public void test01() throws IOException {
        String string = FileUtils.readFileToString(new File("info1.txt"));
        System.out.println(string);
    }

    public static void main(String[] args) throws Exception {
        //test1("myjava/info1.txt");
//		test("myjava/info1.txt");
        test("info1.txt", "utf-8");
    }

    //使用平台默认字符集，编码数据，写入文件
    public static void test1(String filePath) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath)));
        bw.write("中国");
        bw.close();
    }

    //使用平台默认字符集，编码数据，写入文件
    public static void test(String filePath) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath))));
        bw.write("中国");
        bw.close();
    }

    //使用指定字符集，编码数据，写入文件
    public static void test(String filePath, String charSetName) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath)), charSetName));
        bw.write("中国");
        bw.close();
    }

}
