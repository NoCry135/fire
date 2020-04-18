//package com.ca.fire.test.reflect;
//
//import org.junit.Test;
//import org.reflections.Reflections;
//import org.reflections.scanners.FieldAnnotationsScanner;
//import org.reflections.scanners.SubTypesScanner;
//import org.reflections.util.ConfigurationBuilder;
//
//import javax.ws.rs.PathParam;
//import java.util.Set;
//import java.util.regex.Pattern;
//
///**
// * Created on 2020/4/3
// */
//public class TestReflections {
//
//    @Test
//    public void test01() {
//
//// 初始化工具类
//        Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages(basePackages).addScanners(new SubTypesScanner()).addScanners(new FieldAnnotationsScanner()));
//
//// 获取某个包下类型注解对应的类
//        Set<Class<?>> typeClass = reflections.getTypesAnnotatedWith(RpcInterface.class, true);
//
//// 获取子类
//        Set<Class<? extends SomeType>> subTypes = reflections.getSubTypesOf(SomeType.class);
//
//// 获取注解对应的方法
//        Set<Method> resources = reflections.getMethodsAnnotatedWith(SomeAnnotation.class);
//
//// 获取注解对应的字段
//        Set<Field> ids = reflections.getFieldsAnnotatedWith(javax.persistence.Id.class);
//
//// 获取特定参数对应的方法
//        Set<Method> someMethods = reflections.getMethodsMatchParams(long.class, int.class);
//
//        Set<Method> voidMethods = reflections.getMethodsReturn(void.class);
//
//        Set<Method> pathParamMethods = reflections.getMethodsWithAnyParamAnnotated(PathParam.class);
//
//// 获取资源文件
//        Set<String> properties = reflections.getResources(Pattern.compile(".*\\.properties"));
//    }
//}
