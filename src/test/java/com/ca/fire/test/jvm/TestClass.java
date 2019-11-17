package com.ca.fire.test.jvm;

import org.junit.Test;

public class TestClass {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
        //输出了SuperClass init
        //说明子类引用父类定义的静态属性,只会初始化父类,不会初始化子类
//        SuperClass init
//        123
    }

    @Test
    public void test01() {
        System.out.println(3|9);

        String serverGroup = System.getProperty("server.group");
        System.out.println(serverGroup);
        SuperClass[] subClass = new SuperClass[10];
    }
}
