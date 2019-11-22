package com.ca.fire.test.btrace.User;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

/**
 * Created by caian on 2019/11/22
 */

@BTrace
public class PrintSame {

    @OnMethod(
            clazz = "com.ann.boot.controller.UserController",
            method = "same"
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, String name) {
        BTraceUtils.println("ClassName: " + pcn);
        BTraceUtils.println("MethodName: " + pmn);
        BTraceUtils.println("name: " + name);
        BTraceUtils.println();
    }

    @OnMethod(
            clazz = "com.ann.boot.controller.UserController",
            method = "same"
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, Long id, String name) {
        BTraceUtils.println("ClassName: " + pcn);
        BTraceUtils.println("MethodName: " + pmn);
        BTraceUtils.println("id: " + id);
        BTraceUtils.println("name: " + name);
        BTraceUtils.println();
    }
}