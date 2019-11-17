package com.ca.fire.test.thread.exprint;

public class Print429 {

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
        this.notify();
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
        this.notify();
    }


    public static void main(String[] args) {
        Print429 print429 = new Print429();

        new Thread() {

            @Override
            public void run() {
                while (true) {
                    print429.print1();
                }
            }
        }.start();

        new Thread() {

            @Override
            public void run() {
                while (true) {
                    print429.print2();
                }
            }
        }.start();
    }

}
