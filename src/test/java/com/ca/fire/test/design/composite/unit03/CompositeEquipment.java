package com.ca.fire.test.design.composite.unit03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeEquipment extends Equipment {

    private int i = 0;

    //定义一个Vector 用来存放'儿子'
    private List<Equipment> equipment = new ArrayList();

    public CompositeEquipment(String name) {
        super(name);
    }

    @Override
    public double netPrice() {
        double netPrice = 0.;
        Iterator iter = equipment.iterator();
        while (iter.hasNext()) {
            netPrice += ((Equipment) iter.next()).netPrice();
        }
        return netPrice;
    }

    @Override
    public double discountPrice() {
        double discountPrice = 0.;
        Iterator iter = equipment.iterator();
        while (iter.hasNext()) {
            discountPrice += ((Equipment) iter.next()).discountPrice();
        }
        return discountPrice;
    }

    @Override
    public boolean add(Equipment equipment) {

        this.equipment.add(equipment);
        return true;
    }

    @Override
    public boolean remove(Equipment equipment) {
        return super.remove(equipment);
    }

    @Override
    public Iterator iter() {
        return equipment.iterator();
    }
}
