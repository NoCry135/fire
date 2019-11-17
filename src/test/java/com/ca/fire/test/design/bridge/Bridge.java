package com.ca.fire.test.design.bridge;

/**
 * 抽象类,包含接口对象,调用的是接口实现类的方法
 */
public abstract class Bridge {

    private Sourceable source;

    public Sourceable getSource() {
        return source;
    }

    public void setSource(Sourceable source) {
        this.source = source;
    }

    public void method() {
        source.method();
    }
}
