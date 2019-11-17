package com.ca.fire.test.design.builder.unit01;

/**
 * 绍建造者模式的精髓，那就是director
 */
public class Director {

    public Robot createHumanByDirecotr(IBuildRobot bh) {
        bh.buildBody();
        bh.buildFoot();
        bh.buildHand();
        bh.buildHead();
        return bh.createHuman();
    }

}
