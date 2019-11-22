package com.ca.fire.test.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * Created by caian on 2019/11/22
 */
@BTrace
public class TracingScriptDemo {

    @OnMethod(clazz = "com.ann.boot.task.QuartzTaskDemo", method = "executeInternal", location = @Location(value = Kind.RETURN))
    public static void printUserInfo(AnyType params, @Return AnyType type) {
        BTraceUtils.print("=====");
        BTraceUtils.println("====params=====");
        BTraceUtils.printFields(params);
        BTraceUtils.println("====return result=====");
        BTraceUtils.printFields(type);
        BTraceUtils.println();
    }

    /**
     * @param pcn  被拦截的类名
     * @param pmn  被拦截的方法名
     * @param args 被拦截的方法的参数值
     * @ProbeClassName String pcn, // 被拦截的类名
     * @ProbeMethodName String pmn, // 被拦截的方法名
     * AnyType[] args // 被拦截的方法的参数值
     */
    @OnMethod(clazz = "com.ann.boot.task.QuartzTaskDemo", method = "executeInternal", location = @Location(value = Kind.RETURN))
    public static void printparam(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
        BTraceUtils.printArray(args);
        // 打印行
        BTraceUtils.println("className: " + pcn);
        BTraceUtils.println("MethodName: " + pmn);
        BTraceUtils.println();
    }

    @OnMethod(
            clazz = "om.ann.boot.task.QuartzTaskDemo",
            method = "<init>"  // 指定拦截构造函数
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
        BTraceUtils.println("ClassName: " + pcn);
        BTraceUtils.println("MethodName: " + pmn);
        BTraceUtils.printArray(args);
        BTraceUtils.println();
    }
}
