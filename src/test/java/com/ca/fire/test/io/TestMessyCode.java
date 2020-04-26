package com.ca.fire.test.io;

public class TestMessyCode {
    public static void main(String[] args) throws Exception {
        test1();
        test2();
        test3();
        test4();
    }

    // 没有乱码
    public static void test1() {
        // 编码：字符--->字节数组
        String str = "中国";
        byte[] data = str.getBytes();
        // 解码：字节数组--->字符
        String s = new String(data);
        // 编码与解码字符集统一，都采用平台默认字符集，不会乱码
        System.out.println(s);
    }

    // 没有乱码
    public static void test2() throws Exception {
        // 编码：字符--->字节数组
        String str = "中国";
        byte[] data = str.getBytes("utf-8");
        // 解码：字节数组--->字符
        String s = new String(data, "utf-8");
        // 编码与解码字符集统一，都指定使用utf-8
        System.out.println(s);// 乱码
    }

    // 乱码原因之一：编码与解码字符集不统一
    public static void test3() throws Exception {
        /*
         * //获取平台默认字符集 //方式一：sun.jnu.encoding 影响文件名的创建，而 file.encoding 则影响到文件内容
         * System.out.println(System.getProperty("sun.jnu.encoding"));
         * System.out.println(System.getProperty("file.encoding")); // 方法二：
         * java.nio.charset.Charset.defaultCharset();
         * System.out.println(Charset.defaultCharset());
         */

        // 编码：字符--->字节数组
        String str = "中国";
        byte[] data = str.getBytes();
        // 解码：字节数组--->字符
        String s = new String(data, "utf-8");
        // 编码与解码字符集不统一，平台默认字符集是GBK，而解码使用utf-8
        System.out.println(s);// 乱码
    }

    // 乱码原因之二：缺失字节
    public static void test4() throws Exception {
        // 编码：字符--->字节数组
        String str = "中国";
        byte[] data = str.getBytes("utf-8");
        // 解码：字节数组--->字符
        String s = new String(data, 0, 1, "utf-8");
        // 编码与解码字符集统一，都指定使用utf-8
        System.out.println(s);// 乱码
    }

}
