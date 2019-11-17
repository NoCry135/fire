package com.ca.fire.test.event;

import java.util.concurrent.locks.LockSupport;

public class MainTest {
    public static void main(String[] args) {
        System.out.println(000);
        LockSupport.park();
        System.out.println(1111);
        EventSourceObject object = new EventSourceObject();
        //注册监听器
        object.addCusListener(new CusEventListener() {
            @Override
            public void fireCusEvent(CusEvent e) {
                super.fireCusEvent(e);
            }
        });
        //触发事件
        object.setName("eric");
    }
}
