package com.ca.fire.test.design.factory.method;

public class FactoryTest {

    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        Icar bmw = carFactory.pruduce("bmw");
        bmw.run();

        //////////////////
        System.out.println("============================");
        CarFactoryMore carFactoryMore = new CarFactoryMore();
        Icar icar = carFactoryMore.produceAudo();
        icar.run();

        System.out.println("============================");
        Icar icar1 = CarStaticFactory.produceAudo();icar1.run();
        System.out.println("============================");

        BMWFactory bmwFactory = new BMWFactory();
        Icar icar2 = bmwFactory.produceCar();
        icar2.run();

    }
}
