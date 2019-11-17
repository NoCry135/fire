package com.ca.fire.test.design.state.unit03;

public interface ILift {

    int OPENING_STATE = 1;

    int CLOSING_STATE = 2;

    int RUNNING_STATE = 3;

    int STOPPING_STATE = 4;

    //设置电梯的状态
    void setState(int state);

    //首先电梯门开启动作
    void open();

    //电梯门有开启，那当然也就有关闭了

    void close();

    //电梯要能上能下，跑起来
    void run();

    //电梯还要能停下来，停不下来那就扯淡了
    void stop();
}
