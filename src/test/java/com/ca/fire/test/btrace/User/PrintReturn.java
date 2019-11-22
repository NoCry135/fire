package com.ca.fire.test.btrace.User;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * Created by caian on 2019/11/22
 */


@BTrace
public class PrintReturn {

    @OnMethod(
            clazz = "com.ann.boot.controller.UserController",
            method = "getUser",
            location = @Location(Kind.RETURN)  // 拦截返回值
    )
    public static void anyRead(@ProbeClassName String pcn,
                               @ProbeMethodName String pmn,
                               @Return AnyType result) {
        BTraceUtils.println("ClassName: " + pcn);
        BTraceUtils.println("MethodName: " + pmn);
        BTraceUtils.println("ResultValue: " + result);
        BTraceUtils.println();
    }


}