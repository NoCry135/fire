package com.ca.fire.jvmMonitor.annotation;

import com.ca.fire.jvmMonitor.profiler.CallerInfo;
import com.ca.fire.jvmMonitor.profiler.proxy.Profiler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Method;

/**
 * Created on 2019/12/1
 */
@Aspect
public class JAnnotation implements InitializingBean {

    private String systemKey;
    private String jvmKey;

    public JAnnotation() {
    }

    @Pointcut("@annotation(com.ca.fire.jvmMonitor.annotation.JProfiler)")
    public void JAnnotationPoint() {
    }

    @Around("JAnnotationPoint()")
    public Object execJAnnotation(ProceedingJoinPoint jp) throws Throwable {
        Method method = this.getMethod(jp);
        boolean functionerror = false;
        CallerInfo callerInfo = null;

        Object var15;
        try {
            JProfiler anno = (JProfiler) method.getAnnotation(JProfiler.class);
            if (anno != null) {
                String jproKey = anno.jKey();
                if (jproKey != null && !"".equals(jproKey.trim())) {
                    boolean tp = false;
                    boolean heartbeat = false;
                    JProEnum[] monitorState = anno.mState();
                    JProEnum[] var13 = monitorState;
                    int var12 = monitorState.length;

                    for (int var11 = 0; var11 < var12; ++var11) {
                        JProEnum me = var13[var11];
                        if (me.equals(JProEnum.TP)) {
                            tp = true;
                        } else if (me.equals(JProEnum.Heartbeat)) {
                            heartbeat = true;
                        } else if (me.equals(JProEnum.FunctionError)) {
                            functionerror = true;
                        }
                    }

                    callerInfo = Profiler.registerInfo(jproKey, heartbeat, tp);
                }
            }

            var15 = jp.proceed();
        } catch (Throwable var18) {
            if (callerInfo != null && functionerror) {
                Profiler.functionError(callerInfo);
            }

            throw var18;
        } finally {
            if (callerInfo != null) {
                Profiler.registerInfoEnd(callerInfo);
            }

        }

        return var15;
    }

    private Method getMethod(JoinPoint jp) throws Exception {
        MethodSignature msig = (MethodSignature) jp.getSignature();
        Method method = msig.getMethod();
        return method;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.systemKey != null && !"".equals(this.systemKey.trim())) {
            Profiler.InitHeartBeats(this.systemKey);
        }

        if (this.jvmKey != null && !"".equals(this.jvmKey.trim())) {
            Profiler.registerJVMInfo(this.jvmKey);
        }

    }

    public void setJvmKey(String jvmKey) {
        this.jvmKey = jvmKey;
    }
}
