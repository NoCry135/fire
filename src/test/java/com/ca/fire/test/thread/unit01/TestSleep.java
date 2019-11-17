package com.ca.fire.test.thread.unit01;

public class TestSleep {
    public static void main(String[] args) {
        SleepDamo sleepDamo = new SleepDamo();
        Thread sleep = new Thread(sleepDamo, "sleep");
        sleep.start();
        //唤醒睡眠
        sleep.interrupt();
    }


}

class SleepDamo implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("小敏在看书!" + i);

            if (i == 50) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("小敏被打断了,休息一会!");
                }
            }
        }
    }
}