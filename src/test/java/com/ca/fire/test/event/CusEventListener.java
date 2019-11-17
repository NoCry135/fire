package com.ca.fire.test.event;

import java.util.EventListener;

/**
 * 实现java.util.EventListener接口,注册在事件源上,当事件源的属性或状态改变时,取得相应的监听器调用其内部的回调方法。
 */
public class CusEventListener implements EventListener {

    //事件发生后的回调方法
    public void fireCusEvent(CusEvent e) {
        EventSourceObject eObject = (EventSourceObject) e.getSource();
        System.out.println("My name has been changed!");
        System.out.println("I got a new name,named \"" + eObject.getName() + "\"");
    }
}
