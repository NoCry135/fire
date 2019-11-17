package com.ca.fire.test.design.observer.wcat;

public class TestObserver {

    public static void main(String[] args) {
        WechatServer server = new WechatServer();

        MyObserver userZhang = new WxinUser("ZhangSan");
        MyObserver userLi = new WxinUser("LiSi");
        MyObserver userWang = new WxinUser("WangWu");

        server.registerObserver(userZhang);
        server.registerObserver(userLi);
        server.registerObserver(userWang);
        server.setInfomation("PHP是世界上最好用的语言！");

        System.out.println("----------------------------------------------");
        server.removeObserver(userZhang);
        server.setInfomation("JAVA是世界上最好用的语言！");
    }
}
