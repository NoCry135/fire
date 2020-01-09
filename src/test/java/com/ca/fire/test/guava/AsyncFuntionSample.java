package com.ca.fire.test.guava;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncFuntionSample implements AsyncFunction<Long, String> {
    private ConcurrentMap<Long, String> map = Maps.newConcurrentMap();
    private ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    @Override
    public ListenableFuture<String> apply(@NullableDecl Long input) throws Exception {
        return null;
    }

//    @Override
//    public ListenableFuture<String> apply(final Long input) throws Exception {
//        if (map.containsKey(input)) {
//            SettableFuture<String> listenableFuture = SettableFuture.create();
//            listenableFuture.set(map.get(input));
//            return listenableFuture;
//        } else {
//            return listeningExecutorService.submit(new Callable<String>() {
//                @Override
//                public String call() throws Exception {
//                    String retrieved = service.get(input);
//                    map.putIfAbsent(input, retrieved);
//                    return retrieved;
//                }
//            });
//        }
//    }
//    }
}
