package com.ca.fire.test.thread;

import java.util.concurrent.CountDownLatch;


public class ThreadTest01_callable2 {

	public static void main(String[] args) throws Exception {
		
		// 闭锁
		
		long start = System.currentTimeMillis();
		CountDownLatch latch = new CountDownLatch(20);
		
		// 清理库存
		for ( int i = 0; i < 20; i++ ) {
			new Thread(new Runnable(){
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println( Thread.currentThread().getName() + "线程清理完库存" );
					latch.countDown();
				}
			}, "" + i).start();
		}
		
		latch.await();
		
		long end = System.currentTimeMillis();
		
		System.out.println( "清理库存的时间 = " + (end-start) +"ms");
		
	}

}
