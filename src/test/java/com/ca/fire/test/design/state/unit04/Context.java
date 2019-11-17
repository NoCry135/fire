package com.ca.fire.test.design.state.unit04;

public class Context {

    //定义出所有的电梯状态
    public final static LiftState openningState = new OpenningState();

    public final static LiftState closeingState = new ClosingState();

    public final static LiftState runningState = new RunningState();

    public final static LiftState stoppingState = new StoppingState();

    //定一个当前电梯状态
    private LiftState liftState;

    public LiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        //把当前的环境通知到各个实现类中
        this.liftState.setContext(this);
    }

    public void open() {
        this.liftState.open();
    }

    public void close() {
        this.liftState.close();
    }

    public void run() {
        this.liftState.run();
    }

    public void stop() {
        this.liftState.stop();
    }

}
