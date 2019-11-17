package com.ca.fire.test.thread;

public class ThreadTest04_loop {

	public static void main(String[] args) throws Exception {
		
			Thread t1 = new Thread(new Runnable(){
				public void run() {
					for (int i = 1; i <= 5; i++) {
						System.out.println( Thread.currentThread().getName()  + "打印 " + i );
					}
				}
			}, "第一个线程");
			t1.start();
			
			Thread t2 = new Thread(new Runnable(){
				public void run() {
					try {
						t1.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (int i = 1; i <= 10; i++) {
						System.out.println( Thread.currentThread().getName() + "打印 " + i );
					}
				}
			}, "第二个线程");
			t2.start();
			
			Thread t3 = new Thread(new Runnable(){
				public void run() {
					try {
						t2.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (int i = 1; i <= 15; i++) {
						System.out.println( Thread.currentThread().getName() + "打印 " + i );
					}
				}
			}, "第三个线程");
			t3.start();			
	}
}
