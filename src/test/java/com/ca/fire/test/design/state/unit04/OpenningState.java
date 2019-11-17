package com.ca.fire.test.design.state.unit04;

public class OpenningState extends LiftState {

    @Override
    public void open() {
        System.out.println("电梯门开启...");
    }

    @Override
    public void close() {
        //状态修改
        super.context.setLiftState(Context.closeingState);
        //动作委托为CloseState来执行
        super.context.getLiftState().close();
    }

    @Override
    public void run() {
        System.out.println("开门状态不好跑...");

    }

    @Override
    public void stop() {
        System.out.println("开门状态就是停止状态...");

    }
}
