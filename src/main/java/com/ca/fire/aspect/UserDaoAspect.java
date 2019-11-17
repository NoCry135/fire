package com.ca.fire.aspect;

import com.ca.fire.controller.UserController;
import com.ca.fire.domain.bean.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
//@Component
public class UserDaoAspect {
    private static Logger logger = LoggerFactory.getLogger(UserDaoAspect.class);

//    @Pointcut(value = "execution( * com.ca.fire.manager.impl.UserManagerImpl.insert(com.ca.fire.domain.bean.User )) && args(user)")
//    public void pointcut(User user) {
//    }

    @Pointcut(value = "execution( *  com.ca.fire.dao.UserDao.insert(com.ca.fire.domain.bean.User )) && args(user)")
    public void pointcut(User user) {
    }

    @Before(value = "pointcut(user)")
    public void startRecordLog(User user) {
        logger.error(user.getUserName());
        user.setCreateUser("caian");
        user.setUpdateUser("caian");
    }

    public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        Class aClass = joinPoint.getTarget().getClass();
        Annotation[] declaredAnnotations = aClass.getDeclaredAnnotations();
        Method[] methods = aClass.getMethods();
        Annotation[] declaredAnnotations1 = methods[0].getDeclaredAnnotations();

        for (int i = 0; i < declaredAnnotations.length; i++) {
            Class<? extends Annotation> annotationType = declaredAnnotations[i].annotationType();

        }
        String classType = aClass.getName();

        Class<?> clazz = Class.forName(classType);
        Object proceed = joinPoint.proceed();

        return proceed;

    }
}
