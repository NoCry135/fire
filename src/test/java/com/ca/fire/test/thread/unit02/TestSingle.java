package com.ca.fire.test.thread.unit02;

public class TestSingle {
    private TestSingle single;

    private TestSingle() {
    }

    public TestSingle getSingle() {
        if (single == null) { //为了提高效率
            synchronized (TestSingle.class) {
                if (single == null) {//同步
                    single = new TestSingle();
                }
            }

        }

        return single;
    }
}
