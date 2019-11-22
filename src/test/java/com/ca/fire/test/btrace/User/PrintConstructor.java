package com.ca.fire.test.btrace.User;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

/**
 * Created by caian on 2019/11/22
 */

@BTrace
public class PrintConstructor {

    /**
     * 演示拦截构造函数
     *
     * @param pcn
     * @param pmn
     * @param args
     */
    @OnMethod(
            clazz = "com.ann.boot.entity.User",
            method = "<init>"  // 指定拦截构造函数
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
        BTraceUtils.println("ClassName: " + pcn);
        BTraceUtils.println("MethodName: " + pmn);
        BTraceUtils.printArray(args);
        BTraceUtils.println();
    }
}
