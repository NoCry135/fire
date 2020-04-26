package com.ca.fire.test.io;

import java.io.*;

public class TestBufferedStream {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            copyFileBuffer("D:\\download\\101.mp4", "d:/1.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        try {
            copyFile("D:\\download\\101.mp4", "d:/2.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void copyFileBuffer(String srcPath, String destPath) throws IOException {
        File src = new File(srcPath);
        File dest = new File(destPath);

        InputStream input = new BufferedInputStream(new FileInputStream(src));
        OutputStream output = new BufferedOutputStream(new FileOutputStream(dest));
        byte[] data = new byte[1024];
        int len;
        while ((len = input.read(data)) != -1) {
            output.write(data, 0, len);
        }
        input.close();
        output.close();
    }


    /**
     * 字节输入缓冲流BufferedInputStream
     * 字节输出缓冲流BufferedOutputStream
     *
     * @param srcPath
     * @param destPath
     * @throws IOException
     */
    public static void copyFile(String srcPath, String destPath) throws IOException {
        File src = new File(srcPath);
        File dest = new File(destPath);

        InputStream input = new FileInputStream(src);
        OutputStream output = new FileOutputStream(dest);
        //如果此处改为比8192大的，那么这个效率比上面的高，因此上面的从输入流缓冲区到输出流缓冲区使用1024数组转移
        byte[] data = new byte[1024];
        int len;
        while ((len = input.read(data)) != -1) {
            output.write(data, 0, len);
        }
        input.close();
        output.close();
    }
}

