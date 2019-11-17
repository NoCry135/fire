package com.ca.fire.test.design.observer.product;

import java.util.Observable;
import java.util.Observer;

public class OUser implements Observer {

    private String name;

    public OUser() {
    }

    public OUser(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(this.name + "收到商品价格变化" + arg);
    }


}
