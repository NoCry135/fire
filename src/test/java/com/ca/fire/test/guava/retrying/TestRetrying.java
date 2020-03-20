package com.ca.fire.test.guava.retrying;

import com.ca.fire.test.design.composite.unit03.Cabinet;
import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2020/1/20
 */
public class TestRetrying {

    @Test
    public void test() {

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                //重试条件
                .retryIfException()
                .retryIfRuntimeException()
                .retryIfExceptionOfType(Exception.class)
                .retryIfException(Predicates.equalTo(new Exception()))
                .retryIfResult(Predicates.equalTo(Boolean.FALSE))
                //等待策略
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                //停止重试
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                //时间限制
//                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS, Executors.newCachedThreadPool()))
                .build();

        Callable<Boolean> callable = new Callable<Boolean>() {
            int times = 1;

            @Override
            public Boolean call() throws Exception {
                times++;
                System.out.println("thread " + Thread.currentThread().getName() + ", times=" + times);
                if (times == 2) {
                    throw new NullPointerException();
                } else if (times == 3) {
                    throw new Exception();
                } else if (times == 4) {
                    throw new RuntimeException();
                } else if (times == 5) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        try {
            retryer.call(callable);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }
    }
}
