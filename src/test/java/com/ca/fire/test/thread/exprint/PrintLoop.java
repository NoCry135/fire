package com.ca.fire.test.thread.exprint;

public class PrintLoop {

    public static void main(String[] args) {
        PrintTest print = new PrintTest();


        new Thread(new Runnable(){
            public void run() {
                for ( int i = 1; i <= 10; i++ ) {
                    print.print5();
                }
            }
        }, "第一个线程").start();

        new Thread(new Runnable(){
            public void run() {
                for ( int i = 1; i <= 10; i++ ) {
                    print.print10();
                }
            }
        }, "第二个线程").start();

        new Thread(new Runnable(){
            public void run() {
                for ( int i = 1; i <= 10; i++ ) {
                    print.print15();
                }
            }
        }, "第三个线程").start();

//        new Thread() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    printTest.print5();
//                }
//
//            }
//        }.start();
//        new Thread() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    printTest.print10();
//                }
//            }
//        }.start();
//
//        new Thread() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    printTest.print15();
//                }
//            }
//        }.start();
    }
}
