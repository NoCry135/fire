package com.ca.fire.test.thread;

import java.util.concurrent.*;

public class TestThread02 {

    public static void main(String[] args) throws Exception {


        //ExecutorService pools = Executors.newFixedThreadPool(5);
        //ExecutorService pools = Executors.newSingleThreadExecutor();
        //ExecutorService pools = Executors.newCachedThreadPool();
        ScheduledExecutorService pools = Executors.newScheduledThreadPool(5);

        for ( int i = 0; i < 5; i++ ) {
            Future<Integer> result = pools.schedule(new Callable<Integer>(){
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(100);
                    return 100;
                }
            }, 1, TimeUnit.SECONDS);
            System.out.println( "执行结果 = " + result.get() );
        }

        try {

        } finally {
            pools.shutdown();
        }
    }
}
