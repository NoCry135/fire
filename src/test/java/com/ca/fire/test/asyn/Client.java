package com.ca.fire.test.asyn;

import com.ca.fire.test.design.adapter.unit01.Source;
import com.mysql.jdbc.TimeUtil;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Client {

    private TransferService transferService = new TransferServiceImpl(); // 使用依赖注入获取转账服务的实例

    private final static int A = 1000;

    private final static int B = 1001;

    @Test
    public void syncInvoke() throws ExecutionException, InterruptedException {

        // 同步调用

        transferService.transfer(A, B, 100).get();

        System.out.println(" 转账完成！");

    }


    public void asyncInvoke() {

        // 异步调用

        transferService.transfer(A, B, 100)
                .thenRun(() -> System.out.println(" 转账完成！"));
    }

    @Test
    public void test01() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(
                () -> {
                    System.out.println("test task running");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        );
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                new Supplier<String>() {
                    @Override
                    public String get() {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return "test result";
                    }
                }
        );
        String s = future.get();
        System.out.println(s);

    }


    @Test
    public void test03() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "test result";
                }).thenRun(() -> {
            System.out.println("task finished");
        });
        future.get();

    }

    @Test
    public void test04() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "test result";
                }
        ).thenAccept(result -> {
            System.out.println(result);
        });

        Void aVoid = future.get();
    }

    @Test
    public void test05() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "test result";
                }

        ).thenApply(result -> {
            return result + "after thenApply";
        });
        String s = future.get();
        System.out.println(s);

    }

    @Test
    public void test06() throws ExecutionException, InterruptedException {

        CompletableFuture<Double> weightFuture = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 65.0;
                }
        );
        CompletableFuture<Double> heightFuture = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 165.0;
                }
        );

        CompletableFuture<Double> bmiFuture = weightFuture.thenCombine(heightFuture, (weight, height) -> {
            Double heightMeter = height / 100;
            return weight / (heightMeter * heightMeter);
        });
        System.out.println(bmiFuture.get());

    }
}
