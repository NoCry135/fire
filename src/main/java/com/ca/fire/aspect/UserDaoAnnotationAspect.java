package com.ca.fire.aspect;


import com.ca.fire.domain.bean.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserDaoAnnotationAspect {

    @Pointcut("@annotation(com.ca.fire.annotations.MethodAnnotation)")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        Object firstArg = args[0];
        if (firstArg instanceof User) {
            User user = (User) firstArg;
            user.setCreateUser("caian");
            user.setUpdateUser("caian");
        }

    }
}
