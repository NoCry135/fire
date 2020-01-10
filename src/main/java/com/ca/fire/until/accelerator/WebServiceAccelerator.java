package com.ca.fire.until.accelerator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

public class WebServiceAccelerator {
    private static final Logger log = LoggerFactory.getLogger(WebServiceAccelerator.class);

    //默认10个线程
    private final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    private Map<String, Accelerator> callableMap = Maps.newConcurrentMap();


    private Map<String, ListenableFuture<Object>> returnFutureMap = Maps.newConcurrentMap();


    public Map<String, ListenableFuture<Object>> getReturnMap() {
        return returnFutureMap;
    }

    public WebServiceAccelerator addTask(String index, Accelerator accelerator) {
        callableMap.put(index, accelerator);
        return this;
    }

    public void run() {

        List<ListenableFuture<Object>> monitor = Lists.newArrayList();

        for (String i : callableMap.keySet()) {
            ListenableFuture<Object> future = service.submit(callableMap.get(i).create());
            monitor.add(future);
            try {
                returnFutureMap.put(i, future);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        /**获取所有Future,当其中一个Future失时，将会进入失败逻辑onFailure*/
        final ListenableFuture<List<Object>> resultsFuture = Futures.allAsList(monitor);
        try {
            Futures.addCallback(resultsFuture, new FutureCallback<Object>() {
                @Override
                public void onSuccess(Object result) {
                    log.info("异步执行成功");
                }

                @Override
                public void onFailure(Throwable t) {
                    throw new RuntimeException(t.getMessage());
                }
            });
            resultsFuture.get();
        } catch (Exception e) {
            log.error("[多线程]执行任务发生异常", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            service.shutdown();
        }

    }

    public static final WebServiceAccelerator createJob() {
        return new WebServiceAccelerator();
    }
}
