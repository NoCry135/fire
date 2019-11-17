package com.ca.fire.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserParamValidator {

    int min() default 1;

    int max() default 10;

    String value() default "";

    boolean isNotNull() default true;

}
