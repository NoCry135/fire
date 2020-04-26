package com.ca.fire.test.jvm.heap;

import lombok.val;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;

public class TestHeap {

    static class MyHeap {
        public MyHeap() {
        }
    }

    @Test
    public void test02() {
        List<Object> list = new ArrayList<>();
        while (true) {

            list.add(new MyHeap());
        }

    }

    @Test
    public void test03() {
        //获得jvm线程管理bean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor 和synchronizer信息仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("thread id" + threadInfo.getThreadId() + ", name " + threadInfo.getThreadName());
        }
    }


}
