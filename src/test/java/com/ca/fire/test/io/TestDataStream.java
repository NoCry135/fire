package com.ca.fire.test.io;

import java.io.*;

public class TestDataStream {
    public static void main(String[] args) throws Exception {
        save("data.txt");
        read("data.txt");
    }

    public static void read(String filePath) throws Exception {
        DataInputStream dis = new DataInputStream(new FileInputStream(new File(filePath)));

        //读和写因为分开，所以两种之间通常以配置文件的方式交流数据的类型与顺序
        //注意读的顺序，如果顺序不一致，会出错
        System.out.println(dis.readInt());
        System.out.println(dis.readLong());
        System.out.println(dis.readDouble());
        System.out.println(dis.readBoolean());
        System.out.println(dis.readUTF());

        dis.close();
    }

    public static void save(String filePath) throws Exception {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(filePath)));

        //注意写的顺序
        dos.writeInt(100);
        dos.writeLong(100L);
        dos.writeDouble(3.14);
        dos.writeBoolean(false);
        dos.writeUTF("中国");

        dos.close();
    }

}
