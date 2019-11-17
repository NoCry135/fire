package com.ca.fire.test.thread.unit02;

public class TestDeadLock1 {
    public static void main(String[] args) throws InterruptedException {
        BadMan badMan = new BadMan();
        MrLi mrLi = new MrLi();
        DeadLock deadLock = new DeadLock(mrLi, badMan);
        Thread threadA = new Thread(deadLock, "A");
        threadA.start();
        Thread.sleep(100);
        deadLock.setFlag(true);
        Thread threadB = new Thread(deadLock, "B");
        threadB.start();

    }
}

class DeadLock implements Runnable {

    private static BadMan badMan;

    private static MrLi mrLi;

    private volatile boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public DeadLock(MrLi mrLi, BadMan badMan) {
        this.mrLi = mrLi;
        this.badMan = badMan;
    }

    @Override
    public void run() {
        while (true) {
            if (flag) {
                synchronized (badMan) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    badMan.say();
                    synchronized (mrLi) {
                        badMan.get();
                    }
                }
            } else {
                synchronized (mrLi) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mrLi.say();
                    synchronized (badMan) {
                        mrLi.get();
                    }
                }

            }
        }


    }
}

class MrLi {
    public void say() {
        System.out.println("李老汉说给我女儿,我给你钱!!!");
    }

    public void get() {
        System.out.println("李老汉得到了女儿!!!");
    }

}


class BadMan {
    public void say() {
        System.out.println("绑匪说给钱,我给你女儿!!!");
    }

    public void get() {
        System.out.println("绑匪得到了钱!!!");
    }

}