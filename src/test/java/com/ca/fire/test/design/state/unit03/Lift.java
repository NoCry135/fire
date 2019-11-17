package com.ca.fire.test.design.state.unit03;

public class Lift implements ILift {

    private int state;

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void open() {
        switch (this.state) {
            case STOPPING_STATE: //停止状态，淡然要开门了
                this.openWithoutLogic();
                this.setState(OPENING_STATE);
                break;
            case CLOSING_STATE: //如是电梯时关闭状态，则可以开启
                this.openWithoutLogic();
                this.setState(OPENING_STATE);
                break;

            default:
                break;
        }
    }

    @Override
    public void close() {
        switch (this.state) {
            case OPENING_STATE:
                this.closeWithoutLogic();
                this.setState(CLOSING_STATE);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        switch (this.state) {
            case CLOSING_STATE: //如是电梯时关闭状态，则可以运行
                this.runWithoutLogic();
                this.setState(RUNNING_STATE);
                break;
            case STOPPING_STATE: //停止状态，可以运行
                this.runWithoutLogic();
                this.setState(RUNNING_STATE);

            default:
                break;
        }
    }

    @Override
    public void stop() {
        switch (this.state) {
            case CLOSING_STATE: //如是电梯时关闭状态，则当然可以停止了
                this.stopWithoutLogic();
                this.setState(CLOSING_STATE);
                break;
            case RUNNING_STATE: //正在运行状态，有运行当然那也就有停止了
                this.stopWithoutLogic();
                this.setState(CLOSING_STATE);
                break;

            default:
                break;
        }
    }

    //纯粹的电梯关门，不考虑实际的逻辑
    private void closeWithoutLogic() {
        System.out.println("电梯门关闭...");
    }

    //纯粹的店门开，不考虑任何条件
    private void openWithoutLogic() {
        System.out.println("电梯门开启...");
    }

    //纯粹的运行，不考虑其他条件
    private void runWithoutLogic() {
        System.out.println("电梯上下跑起来...");
    }

    //单纯的停止，不考虑其他条件
    private void stopWithoutLogic() {
        System.out.println("电梯停止了...");
    }
}
