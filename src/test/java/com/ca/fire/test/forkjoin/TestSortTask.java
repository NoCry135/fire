package com.ca.fire.test.forkjoin;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class TestSortTask {
    @Test
    public void testSort() {
        long[] array = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        SortTask sortTask = new SortTask(array);
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(sortTask);
        pool.shutdown();
        try {
            pool.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
