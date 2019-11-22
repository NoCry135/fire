package com.ca.fire.test.btrace.User;

import com.ca.fire.domain.bean.User;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import java.lang.reflect.Field;

/**
 * Created by caian on 2019/11/22
 */

@BTrace
public class PrintArgComplex {

    @OnMethod(
            clazz = "com.ann.boot.controller.UserController",
            method = "arg2",
            location = @Location(Kind.ENTRY)
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, User user) {
        //print all fields
        BTraceUtils.print("print all fields: ");
        BTraceUtils.printFields(user);

        //print one field
        Field oneFiled = BTraceUtils.field("com.ann.boot.entity.User", "name");
        BTraceUtils.println("print one field: " + BTraceUtils.get(oneFiled, user));

        BTraceUtils.println("ClassName: " + pcn);
        BTraceUtils.println("MethodName: " + pmn);
        BTraceUtils.println();
    }
}
