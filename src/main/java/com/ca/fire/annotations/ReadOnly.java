package com.ca.fire.annotations;

import java.lang.annotation.*;

/**
 * 声明注解,此注解用于在DAO层识别为访问从库
 */
@Inherited
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOnly {
	String value() default "";
}
