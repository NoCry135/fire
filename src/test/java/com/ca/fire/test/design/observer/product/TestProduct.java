package com.ca.fire.test.design.observer.product;

public class TestProduct {

    public static void main(String[] args) {

        Product product = new Product();
        product.setName("小米手机");

        OUser user1 = new OUser("小王");
        OUser user2 = new OUser("小张");
        OUser user3 = new OUser("小李");
        OUser user4 = new OUser("小刘");

        product.addObserver(user1);
        product.addObserver(user2);
        product.addObserver(user3);
        product.addObserver(user4);
        product.setPrice(1000.00);
        product.notifyObservers(product);
        System.out.println("手机涨价了======================");
        product.setPrice(1200.00);
        product.notifyObservers(product);

    }
}
