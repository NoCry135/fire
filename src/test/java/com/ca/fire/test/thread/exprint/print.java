package com.ca.fire.test.thread.exprint;

public class print {

    private boolean flag;


    public synchronized void print1() {

        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(1);
        flag = true;
        this.notifyAll();
    }


    public synchronized void print2() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(2);
        flag = false;
        this.notifyAll();
    }

    public static void main(String[] args) {
        print print = new print();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    print.print1();
                }

            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    print.print2();
                }
            }
        }.start();
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
