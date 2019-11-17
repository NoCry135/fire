package com.ca.fire.test.design.single;

/**
 * 懒汉式
 * 不急于创建对象
 */
public class LazySingleClass {

    /**
     * 构造器私有化
     */
    private LazySingleClass() {

    }

    /**
     * 私有静态变量
     */
    private static LazySingleClass singleClass = null;


    /**
     * 公共的静态方法
     */

    public static synchronized LazySingleClass getSingleClass() {
        if (singleClass == null) {
            singleClass = new LazySingleClass();
        }

        return singleClass;
    }

}
