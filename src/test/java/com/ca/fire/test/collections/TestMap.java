package com.ca.fire.test.collections;

import com.ca.fire.domain.bean.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TestMap {

    @Test
    public void test01() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        Object put = hashMap.put(null, "111");
        System.out.println(hashMap.size());
    }

    @Test
    public void test02() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(1, "111");
        hashMap.put(2, "222");
        hashMap.put(3, "333");
        Collection<Object> values = hashMap.values();
        System.out.println(hashMap.size());
    }

    @Test
    public void test03() {
        HashMap<Object, User> hashMap = new HashMap<>();
        User user = new User();
        hashMap.put(1, user);
        user.setUserName("222");
        System.out.println(hashMap);
        User obj = hashMap.get("2");
        if (obj == null) {
            obj = new User();
        }
        obj.setUserName("333");

        System.out.println(hashMap);
        HashMap<Object, List<User>> map = new HashMap<>();

        List<User> users = new ArrayList<>();
        map.put("users", users);
        users.add(user);
        System.out.println(map);
    }

}
