package com.ca.fire.test.design.strategy.unit02;

public class Context {

    //构造函数，你要使用那个妙计
    private IStrategy straegy;

    public Context(IStrategy straegy) {
        this.straegy = straegy;
    }

    //使用计谋了，看我出招了
    public void operate(){
        this.straegy.operate();
    }
}
