package com.ca.fire.test.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

import java.util.Map;

@BTrace
public class TracingScript {

//监控某一个方法的执行时间

    /**
     * clazz 指定类
     * method 指定方法
     * 执行println 的内容
     * 说明方法被调用
     */
    @OnMethod(clazz = "com.ann.boot.task.QuartzTaskDemo", method = "executeInternal")
    public static void execute() {
        println("ttt");
    }


    /**
     * @param @Self     object	调用对象
     * @param id        参数
     * @param @Return   map	返回值
     * @param @Duration time	响应时间
     * @author leopard on 2018年6月7日
     */
    @OnMethod(clazz = "cn.leopard.sharding.resource.TestResource", method = "testUrl", location = @Location(Kind.RETURN))
    public static void traceExecute(@Self Object object, String id, @Return Map<String, String> map, @Duration long time) {
        println("调用堆栈！！");
        println("parameter  object = " + object);
        println("parameter  id = " + id);
        println("result  map = " + map);
        println("cost  time = " + time);
        jstack();
    }

    @OnMethod(clazz = "com.ann.boot.task.QuartzTaskDemo", method = "executeInternal", location = @Location(value = Kind.RETURN))
    public static void printUserInfo(AnyType params, @Return AnyType type) {
        BTraceUtils.print("=====");
        BTraceUtils.println("====params=====");
        BTraceUtils.printFields(params);
        BTraceUtils.println("====return result=====");
        BTraceUtils.printFields(type);
        BTraceUtils.println();
    }
}
