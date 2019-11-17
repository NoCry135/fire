package com.ca.fire.aspect;

import com.ca.fire.domain.bean.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 切入接口时基于注解的方式不要使了,可以使用基于表达式的方式,
 * 获取方法签名,判断方法参数
 */
@Aspect
@Component
public class UserDaoAspectPlus {
    private static Logger logger = LoggerFactory.getLogger(UserDaoAspectPlus.class);


    @Pointcut(value = "execution( *  com.ca.fire.dao.UserDao.*(.. ))")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void startRecordLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        if ("insert".equals(name)) {
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
        } else if ("insertBatch".equals(name)) {
            logger.error("....");

        } else {
            logger.error("======....");
        }

    }
}
