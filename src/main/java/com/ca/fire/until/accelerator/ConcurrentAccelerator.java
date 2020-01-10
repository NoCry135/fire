package com.ca.fire.until.accelerator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;

public abstract class ConcurrentAccelerator<T> {
    private static final Logger log = LoggerFactory.getLogger(ConcurrentAccelerator.class);
    private CopyOnWriteArrayList result;
    private List<T> parameters;
    private int capacity;

    public ConcurrentAccelerator(List<T> parameters, CopyOnWriteArrayList result, int capacity) {
        this.parameters = parameters;
        this.result = result;
        this.capacity = capacity;
    }

    //默认10个线程
    private final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));


    private Map<String, ConcurrentInvoker> callableMap = Maps.newConcurrentMap();


    private List<List<T>> subList(List<T> list) {
        return Lists.partition(list, this.capacity);
    }

    public ConcurrentAccelerator addTask(String index, ConcurrentInvoker accelerator) {
        callableMap.put(index, accelerator);
        return this;
    }

    public void run() {
        long t1 = System.currentTimeMillis();
        List<ListenableFuture<Object>> monitor = Lists.newArrayList();
        for (String i : callableMap.keySet()) {
            ListenableFuture<Object> future = service.submit(callableMap.get(i).invoke());
            monitor.add(future);
        }
        /**获取所有Future,当其中一个Future失时，将会进入失败逻辑onFailure*/
        final ListenableFuture<List<Object>> resultsFuture = Futures.allAsList(monitor);
        try {
            Futures.addCallback(resultsFuture, new FutureCallback<Object>() {
                @Override
                public void onSuccess(Object result) {
                    log.info("[多线程]执行任务成功");
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
            log.info("[多线程]执行任务总耗时：" + (System.currentTimeMillis() - t1));
        }

    }

    public ConcurrentAccelerator buildCallableMap() {
        if (CollectionUtils.isEmpty(this.parameters)) {
            throw new RuntimeException("入参不能为空");
        } else {

            List<List<T>> subList = this.subList(this.parameters);
            for (int i = 0; i < subList.size(); i++) {
                operation(subList.get(i));
            }
            return this;
        }
    }

    protected abstract void operation(List subList);

    public static void main(String[] args) {
        final List<String> parameters = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "L", "M", "N", "o", "p");
        final CopyOnWriteArrayList result = new CopyOnWriteArrayList();
        long st = System.currentTimeMillis();
        ConcurrentAccelerator<String> accelerator = new ConcurrentAccelerator(parameters, result, 5) {
            @Override
            protected void operation(List subList) {
                this.addTask(UUID.randomUUID().toString(), new ConcurrentInvoker(subList) {
                    @Override
                    public Object run(List subList) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for (Object s : subList) {
                            result.add(s);
                        }
                        return null;
                    }
                });
            }
        };
        System.out.println("parameter:" + parameters.size());

        accelerator.buildCallableMap().run();
        long en = System.currentTimeMillis();
        System.out.println("spend:" + (en - st));
        System.out.println(result.toString() + "------" + result.size());
    }
}
