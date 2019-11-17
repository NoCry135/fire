package com.ca.fire.test.design.adapter.unit02;

import com.ca.fire.test.design.adapter.unit01.Source;
import com.ca.fire.test.design.adapter.unit01.Targetable;

/**
 * 持有Source类的实例，以达到解决兼容性的问题
 */
public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source) {
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("执行方法2!");
    }
}
