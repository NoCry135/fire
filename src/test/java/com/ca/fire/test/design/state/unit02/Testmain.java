package com.ca.fire.test.design.state.unit02;
// 定义对象间的一种一对多的依赖关系,当一个对象的状态发生改变时,所有依赖于它的对象都得到通知并被自动更新。
public class Testmain {

    public static void main(String[] args) {
        Context ctx1 = new Context();
        ctx1.setWeather(new Sunshine());
        System.out.println(ctx1.weatherInfo());
        System.out.println("===================");
        ctx1.setWeather(new Rain());
        System.out.println(ctx1.weatherInfo());
    }
}
