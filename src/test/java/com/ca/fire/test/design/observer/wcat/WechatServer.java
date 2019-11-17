package com.ca.fire.test.design.observer.wcat;

import java.util.ArrayList;
import java.util.List;

public class WechatServer implements MyObserverable {

    //注意到这个List集合的泛型参数为Observer接口，设计原则：面向接口编程而不是面向实现编程
    //被观察者里面有观察者的对对象,他需要知道去通知哪些观察者
    private List<MyObserver> list;
    private String message;

    public WechatServer() {
        list = new ArrayList<MyObserver>();
    }

    @Override
    public void registerObserver(MyObserver o) {

        list.add(o);
    }

    @Override
    public void removeObserver(MyObserver o) {
        if (!list.isEmpty())
            list.remove(o);
    }

    //遍历
    @Override
    public void notifyObserver() {
        for (int i = 0; i < list.size(); i++) {
            MyObserver oserver = list.get(i);
            oserver.update(message);
        }
    }

    public void setInfomation(String s) {
        this.message = s;
        System.out.println("微信服务更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }
}
