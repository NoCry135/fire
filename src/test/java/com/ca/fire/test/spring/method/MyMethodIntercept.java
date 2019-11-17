package com.ca.fire.test.spring.method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyMethodIntercept implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String className = methodInvocation.getMethod().getDeclaringClass().getName();
        String methodName = methodInvocation.getMethod().getName();
        System.out.println("className" + "." + "methodName" + ":" + className + "." + methodName);
        Object proceed = methodInvocation.proceed();
        return proceed;
    }
}
