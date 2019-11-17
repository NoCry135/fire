package com.ca.fire.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Aspect
@Component
public class MyPropAspect {

    @Pointcut("@annotation(com.ca.fire.annotations.MyPropAnnotation)")
    public void pointcut() {
    }


    @Before(value = "pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        for (Object arg : args) {
            if (arg instanceof List) {
                List<?> source = (List<?>) arg;
                for (Object obj : source) {
                    setUpdateUser(obj);
                }
            } else {
                setUpdateUser(arg);
            }


        }

    }

    private void setUpdateUser(Object obj) throws IllegalAccessException {
        Class clazz = obj.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            String name = field.getName();
            Object type = field.getType();
            if ("createUser".equals(name) && type instanceof String) {
                field.set(obj, "liwen135");
                return;
            }
        }

        declaredFields = clazz.getSuperclass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            String name = field.getName();
            //type是class
            Object type = field.getType();
            if ("createUser".equals(name) && type.equals(String.class)) {
                field.set(obj, "liwen135");
                return;
            }
        }

    }

}
