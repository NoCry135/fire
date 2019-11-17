package com.ca.fire.aspect;

import com.ca.fire.annotations.UserParamValidator;

import java.lang.reflect.Field;

public class UserParamValidatorAspect {

    public static void annoProcess(Object annotation) throws IllegalAccessException {

        for (Field field : annotation.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(UserParamValidator.class)) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                if(type.equals(String.class)){
                    UserParamValidator validator = field.getAnnotation(UserParamValidator.class);
                    field.set(annotation, validator.value());
                }

            }
        }
    }

}
