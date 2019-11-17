package com.ca.fire.test.design.adapter.unit02;

import com.ca.fire.test.design.adapter.unit01.Source;

/**
 * 对象的适配器模式 基本思路和类的适配器模式相同，只是将Adapter类作修改，这次不继承Source类，而是持有Source类的实例，以达到解决兼容性的问题
 */
public class AdapterTest {

    public static void main(String[] args) {
        Source source = new Source();

        Wrapper wrapper = new Wrapper(source);
        wrapper.method1();
        wrapper.method2();
    }
}
