package com.ca.fire.test.thread;

import com.ca.fire.domain.bean.User;

public class UnSafeVariableTest {

    private User user = new User();


    public void setNameLisi() {
        user.setUserName("lisi");
        System.out.println(user.getUserName());
    }

    public void setNameZhangsan() {
        user.setUserName("zhangsan");
        System.out.println(user.getUserName());
    }

    public void setNameWangwu() {
        user.setUserName("wangwu");
        System.out.println(user.getUserName());
    }

    public void setNameZhaoliu() {
        user.setUserName("zhaoliu");
        System.out.println(user.getUserName());
    }

    /**
     *省略。。。。
     */
}
