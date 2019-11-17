package com.ca.fire.test.spring.bean;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class ChangeMethodBeanNew implements MethodReplacer {

    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("新需求替换了源方法...");
        return null;
    }
}
