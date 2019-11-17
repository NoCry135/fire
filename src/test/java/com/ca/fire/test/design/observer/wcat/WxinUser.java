package com.ca.fire.test.design.observer.wcat;

public class WxinUser implements MyObserver {

    private String name;

    private String message;

    public WxinUser() {
    }

    public WxinUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void read() {
        System.out.println(name + " 收到推送消息： " + message);
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }
}
