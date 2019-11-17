package com.ca.fire.test.design.observer;

/**
 * 观察者李四
 * 类比java.util.MyObserver
 */
public interface ILiSi {

    //一发现别人有动静，自己也要行动起来
    void update(String context);
}
