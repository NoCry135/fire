package com.ca.fire.test.design.proxy.dao.impl;

import com.ca.fire.test.design.proxy.dao.BaseDao;

public class UserDao implements BaseDao {
    @Override
    public Integer insert() {
        return null;
    }

    @Override
    public Integer update() {
        try {
            System.out.println("睡三秒");
            Thread.sleep(3000);
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
