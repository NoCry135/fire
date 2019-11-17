package com.ca.fire.test.design.state.unit04;

public class RunningState extends LiftState {

    @Override
    public void open() {
        System.out.println("电梯运行中不可以开门...");

    }

    @Override
    public void close() {
        System.out.println("电梯运行中门应该是关着的...");

    }

    @Override
    public void run() {
        System.out.println("电梯上下跑...");
    }

    @Override
    public void stop() {
        super.context.setLiftState(Context.stoppingState); //环境设置为停止状态；
        super.context.getLiftState().stop();
    }
}
