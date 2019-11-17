package com.ca.fire.test.design.builder.unit01;

public class SmartManBuilder implements IBuildRobot {

    private Robot robot;

    public SmartManBuilder() {
        this.robot = new Robot();
    }

    @Override
    public void buildHead() {
        robot.setHead("智商180!");
    }

    @Override
    public void buildBody() {
        robot.setBody("身体很好");
    }

    @Override
    public void buildHand() {
        robot.setHand("手很大");
    }

    @Override
    public void buildFoot() {
        robot.setFoot("有点瘸");
    }

    @Override
    public Robot createHuman() {
        return robot;
    }
}
