package com.ca.fire.test.threadlocal;

import com.ca.fire.domain.bean.User;

public class Test01 {

    public static void main(String[] args) {
        ThreadLocal<User> userThreadLocal = new ThreadLocal<User>();
        ThreadLocal<String> localString = new ThreadLocal<String>();

        User user = new User();
        userThreadLocal.set(user);
        User user1 = userThreadLocal.get();
        System.out.println(user1);
        Thread thread = Thread.currentThread();
        System.out.println(thread);
        localString.set("123");
        System.out.println(thread);

    }
}
