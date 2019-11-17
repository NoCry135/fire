package com.ca.fire.test.design.state.unit03;

public class Client {

    public static void main(String[] args) {
        ILift lift = new Lift();
        //首先是电梯门开启，人进去
//电梯的初始条件应该是停止状态
        lift.setState(ILift.RUNNING_STATE);
        lift.open();
        //然后电梯门关闭

        lift.close();
        //再然后，电梯跑起来，向上或者向下

        lift.run();
        //最后到达目的地，电梯挺下来

        lift.stop();
    }
}
