package com.ca.fire.test.io;

import java.io.*;

public class TestBufferedReaderWriter {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            copyFileBuffer("com/ca/fire/test/io/TestBufferedStream.java", "d:/1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        try {
            copyFile("com/ca/fire/test/io/TestBufferedStream.java", "d:/2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void copyFileBuffer(String srcPath, String destPath) throws IOException {
        File src = new File(srcPath);
        File dest = new File(destPath);

        //这儿要使用readLine和newLine方法，就不能使用多态了
        BufferedReader input = new BufferedReader(new FileReader(src));
        BufferedWriter output = new BufferedWriter(new FileWriter(dest));
        String line;
        while ((line = input.readLine()) != null) {
            output.write(line);
            output.newLine();
        }

        input.close();
        output.close();
    }

    public static void copyFile(String srcPath, String destPath) throws IOException {
        File src = new File(srcPath);
        File dest = new File(destPath);

        Reader input = new FileReader(src);
        Writer output = new FileWriter(dest);
        char[] data = new char[1024];
        int len;
        while ((len = input.read(data)) != -1) {
            output.write(data, 0, len);
        }
        input.close();
        output.close();
    }

}
