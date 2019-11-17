package com.ca.fire.annotations;

import java.lang.annotation.*;

/**
 * 注解，用于AOP切方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface MethodAnnotation {
}
