package com.ca.fire.test.thread.local;

public class MyData {

    private String data;

    {
        System.out.println("普通代码块");
    }

    static {
        System.out.println("静态代码块");
    }

    public MyData() {
        System.out.println("构造器");
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void printData(MyData data) {
        System.out.println("测试打印数据:" + data.getData());
    }

    @Override
    public String toString() {
        return "MyData{" +
                "data='" + data + '\'' +
                '}';
    }
}
