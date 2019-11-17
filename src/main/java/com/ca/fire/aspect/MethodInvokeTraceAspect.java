package com.ca.fire.aspect;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Aspect
@Component
public class MethodInvokeTraceAspect {
    private static final Logger logger = LoggerFactory.getLogger(MethodInvokeTraceAspect.class);

    /**
     * 增加一个钩子线程，在程序停止的时候执行，打印方法调用链在程序启动以来的执行次数
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("MethodInvoke traceInfo2InvokeCountMap:" + traceInfo2InvokeCountMap);
                } catch (Exception e) {
                    logger.error("MethodInvoke traceInfo2InvokeCountMap error", e);
                }
            }
        }));
    }

    /**
     * 调用链信息->次数
     */
    private static final Map<String, Integer> traceInfo2InvokeCountMap = new HashMap<String, Integer>();

    /**
     * 创建一个可缓存的线程池，可能会造成线程数过多，需要测试
     */
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Pointcut("@within(com.ca.fire.annotations.MyPropAnnotation)")
//    @Pointcut("@within( com.ca.fire.controller*.*(..))")
    public void pointcut() {

    }

    /**
     * 所以考虑通过AOP对其进行切面处理获取调用时的栈信息，bizType和callCode的值
     * bizType和callCode可以确定调用的是哪个模块的哪个服务
     * 为了防止加入AOP后对方法处理性能造成的影响开启一个异步的线程来获取栈信息和bizType、callCode(此处还需要测试环境和UAT严格测试下)
     *
     * @param joinPoint
     * @throws Throwable
     */
    @After(value = "pointcut()")
    public void doAfter(final JoinPoint joinPoint) throws Throwable {
        try {
            Callable callable = new Callable() {
                @Override
                public Object call() throws Exception {
                    Object[] args = joinPoint.getArgs();
                    /**
                     * 通过webservice调用其他模块时，queryWs和processWs的入参都是两个，都是字符串
                     * 第一个是Token的json串，第二个是方法入参对象的json串
                     */
                    List<String> infoList = new LinkedList<String>();
                    infoList.add("token:" + args[0]);
                    Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
                    for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
                        for (StackTraceElement stackTraceElement : entry.getValue()) {
                            String className = stackTraceElement.getClassName();
                            if (className.startsWith("com.ca.fire.controller")) {
                                infoList.add(0, stackTraceElement.toString());
                            }
                        }
                    }
                    String traceInfo = StringUtils.join(infoList, "=>>");
                    /**
                     * 如果调用链信息已经被切面切过，则将traceInfo设置为null，下次gc的时候系统进行回收
                     * 如果没有被切过，则将调用链信息输出到日志文件中
                     */
                    if (traceInfo2InvokeCountMap.containsKey(traceInfo)) {
                        traceInfo2InvokeCountMap.put(traceInfo, traceInfo2InvokeCountMap.get(traceInfo) + 1);
                        traceInfo = null;
                    } else {
                        traceInfo2InvokeCountMap.put(traceInfo, 1);
                        logger.info("MethodInvokeTraceAspect doAfter infoTrace:" + traceInfo);
                    }
                    return null;
                }
            };
            executorService.submit(callable);
        } catch (Exception e) {
            logger.error("MethodInvokeTraceAspect doAfter error", e);
        }
    }


}
