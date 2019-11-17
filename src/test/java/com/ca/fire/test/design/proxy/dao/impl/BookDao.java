package com.ca.fire.test.design.proxy.dao.impl;

import com.ca.fire.test.design.proxy.dao.BaseDao;

public class BookDao implements BaseDao {
    @Override
    public Integer insert() {
        return null;
    }

    @Override
    public Integer update() {
        try {
            System.out.println("睡一秒");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete() {
        return null;
    }
}
