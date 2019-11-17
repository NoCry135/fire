package com.ca.fire.test.design.single;

/**
 * 饿汉式
 * 立马创建对象
 */
public class HungrySingleClass {
    /**
     * 构造器私有化
     */
    private HungrySingleClass() {
    }

    /**
     * 静态实例对象
     */
    private static HungrySingleClass singleClass = new HungrySingleClass();


    public HungrySingleClass getSingleClass() {
        return singleClass;
    }


}
