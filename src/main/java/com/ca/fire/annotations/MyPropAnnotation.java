package com.ca.fire.annotations;

import java.lang.annotation.*;

/**
 * 1.注解的定义：Java文件叫做Annotation，用@interface表示。
 * <p>
 * 2.元注解：@interface上面按需要注解上一些东西，包括@Retention、@Target、@Document、@Inherited四种。
 * <p>
 * 3.注解的保留策略：
 * <p>
 * 　　@Retention(RetentionPolicy.SOURCE)   // 注解仅存在于源码中，在class字节码文件中不包含
 * <p>
 * 　　@Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得
 * <p>
 * 　　@Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
 * <p>
 * 4.注解的作用目标：
 * <p>
 * 　　@Target(ElementType.TYPE)               // 接口、类、枚举、注解
 * <p>
 * 　　@Target(ElementType.FIELD)             // 字段、枚举的常量
 * <p>
 * 　　@Target(ElementType.METHOD)            // 方法
 * <p>
 * 　　@Target(ElementType.PARAMETER)        // 方法参数
 * <p>
 * 　　@Target(ElementType.CONSTRUCTOR)      // 构造函数
 * <p>
 * 　　@Target(ElementType.LOCAL_VARIABLE)   // 局部变量
 * <p>
 * 　　@Target(ElementType.ANNOTATION_TYPE) // 注解
 * <p>
 * 　　@Target(ElementType.PACKAGE)               // 包
 * <p>
 * 5.注解包含在javadoc中：
 * <p>
 * 　　@Documented
 * <p>
 * 6.注解可以被继承：
 * <p>
 * 　　@Inherited
 * <p>
 * 7.注解解析器：用来解析自定义注解。
 */


@Target({ElementType.FIELD, ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyPropAnnotation {
}

