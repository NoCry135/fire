package com.ca.fire.test.jvm.ex;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class TestDirectMemoryOut {

    public static void main(String[] args) throws InterruptedException {
//        System.out.println("配置的最大直接内存:" + sun.misc.VM.maxDirectMemory() / (double) 1024/1024 + "MB");
        TimeUnit.SECONDS.sleep(3);
        ByteBuffer.allocateDirect(5 * 1024 *1024);
    }
}
