package com.ca.fire.test.design.composite.unit03;

public class TestComposote {

    public static void main(String[] args) {
        Cabinet cabinet = new Cabinet("Tower");
        Chassis chassis = new Chassis("PC Chassis");
        //将PC Chassis 装到Tower 中 (将盘盒装到箱子里)
        cabinet.add(chassis);
        //将一个10GB 的硬盘装到 PC Chassis (将硬盘装到盘盒里)
        chassis.add(new Disk("10 GB"));
        //调用 netPrice()方法;
        System.out.println("netPrice=" + cabinet.netPrice());
        System.out.println("discountPrice=" + cabinet.discountPrice());
    }
}
