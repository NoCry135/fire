package com.ca.fire.test.design.composite.unit03;

import java.util.Iterator;

public abstract class Equipment {
    private String name;

    //网络价格
    public abstract double netPrice();

    //折扣价格
    public abstract double discountPrice();

    //增加部件方法
    public boolean add(Equipment equipment) {
        return false;
    }

    //删除部件方法
    public boolean remove(Equipment equipment) {
        return false;
    }

    //注意这里，这里就提供一种用于访问组合体类的部件方法。
    public Iterator iter() {
        return null;
    }

    public Equipment(final String name) {
        this.name = name;
    }

}
