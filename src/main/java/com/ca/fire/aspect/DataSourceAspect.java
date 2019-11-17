package com.ca.fire.aspect;

import com.ca.fire.until.DynamicDataSourceHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    // private static Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    //方法执行的前后调用
    @Around("@annotation(com.ca.fire.annotations.ReadOnly)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object obj = null;
        try {
            DynamicDataSourceHolder.putDataSource("slave");
            obj = pjp.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            DynamicDataSourceHolder.removeDataSource();
        }
        return obj;
    }

}
