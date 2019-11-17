package com.ca.fire.test.design.observer;

/**
 * 具体的被观察者
 */
public class HanFeiZi implements IHanFeiZi {


    private ILiSi liSi;

    //韩非子是否在吃饭，作为监控的判断标准
    private boolean isHaveBreakfast = false;

    //韩非子是否在娱乐
    private boolean isHaveFun = false;

    public boolean isHaveBreakfast() {
        return isHaveBreakfast;
    }

    public void setHaveBreakfast(boolean haveBreakfast) {
        isHaveBreakfast = haveBreakfast;
    }

    public boolean isHaveFun() {
        return isHaveFun;
    }

    public void setHaveFun(boolean haveFun) {
        isHaveFun = haveFun;
    }

    public ILiSi getLiSi() {
        return liSi;
    }

    public void setLiSi(ILiSi liSi) {
        this.liSi = liSi;
    }

    @Override
    public void haveBreakfast() {
        System.out.println("韩非子:开始吃饭了...");
        this.isHaveBreakfast = true;
        notifyLisi("韩非子:开始吃饭了...");
    }

    @Override
    public void haveFun() {
        System.out.println("韩非子:开始娱乐了...");
        this.isHaveFun = true;
        notifyLisi("韩非子:开始娱乐了...");

    }

    private void notifyLisi(String message) {
        liSi.update(message);
    }

    public HanFeiZi(ILiSi liSi) {
        this.liSi = liSi;
    }

    public HanFeiZi() {
    }
}
