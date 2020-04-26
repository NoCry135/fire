package com.ca.fire.test.forkjoin;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class SortTask extends RecursiveAction {
    private final long[] array;
    private final int lo;
    private final int hi;
    private int THRESHOLD = 0;


    public SortTask(long[] array) {
        this.array = array;
        this.lo = 0;
        this.hi = array.length - 1;
    }

    public SortTask(long[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected void compute() {
        if (lo < hi) {
            int pivot = partition(array, lo, hi);
            SortTask left = new SortTask(array, lo, pivot - 1);
            SortTask right = new SortTask(array, pivot + 1, hi);
            left.fork();
            right.fork();
            left.join();
            right.join();
        }
    }

    private int partition(long[] array, int lo, int hi) {
        long x = array[hi];
        int i = lo - 1;
        for (int j = 0; j < hi; j++) {
            if (array[j] <= x) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, hi);
        return i + 1;
    }

    private void swap(long[] array, int i, int j) {
        if (i != j) {
            long tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }


}
