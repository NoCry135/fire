package com.ca.fire.test.design.bridge;

/**
 * 就通过对Bridge类的调用，实现了对接口Sourceable的实现类SourceSub1和SourceSub2的调用。
 */
public class BridgeTest {
    public static void main(String[] args) {
        Bridge bridge = new MyBridge();


        SourceSub1 sourceSub1 = new SourceSub1();
        bridge.setSource(sourceSub1);
        bridge.method();

        SourceSub2 sourceSub2 = new SourceSub2();
        bridge.setSource(sourceSub2);
        bridge.method();

    }
}
