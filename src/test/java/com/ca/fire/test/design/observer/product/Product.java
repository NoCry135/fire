package com.ca.fire.test.design.observer.product;

import java.util.Observable;

/**
 * 被观察者,负责通知用户
 */
public class Product extends Observable {


    private String name;

    private double price;


    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        setChanged();
    }

    public void slefMethod() {
        System.out.println("产品使用说明!");
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
