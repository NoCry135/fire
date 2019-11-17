package com.ca.fire.test.design.composite.unit03;

public class Cabinet extends CompositeEquipment {
    public Cabinet(String name) {
        super(name);
    }

    public double netPrice() {
        return 1. + super.netPrice();
    }

    public double discountPrice() {
        return .5 + super.discountPrice();
    }

}
