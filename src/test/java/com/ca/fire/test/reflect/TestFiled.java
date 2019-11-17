package com.ca.fire.test.reflect;

import com.ca.fire.domain.bean.User;
import org.junit.Test;

import java.lang.reflect.Field;

public class TestFiled {
    @Test
    public  void testuser(){
        User user = new User();
        Class<? extends User> aClass = user.getClass();
//        Field[] declaredFields = aClass.getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            System.out.println(declaredField.getName());
//        }
        Field[] fields = aClass.getFields();
        for (Field declaredField : fields) {
            declaredField.setAccessible(true);
            System.out.println(declaredField.getName());
        }

    }
}
