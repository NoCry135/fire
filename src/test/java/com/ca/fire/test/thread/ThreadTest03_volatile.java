package com.ca.fire.test.thread;

import java.util.concurrent.atomic.AtomicInteger;

class Product1 {
	
	public volatile int num = 0;
	public AtomicInteger ai = new AtomicInteger(0);
	
	public void increment() {
		//num++; // temp = num; i = i + 1;
		ai.getAndIncrement();
	}
	
	public void decrement() {
		//num--;
		ai.getAndDecrement();
	}
}
public class ThreadTest03_volatile {

	public static void main(String[] args) throws Exception {
		// 开发程序时，遵循高内聚，低耦合
		Product1 product = new Product1();
		
	    Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				for ( int i = 0; i < 10000; i++ ) {
					product.increment();
				}
					
			}
		});
	    t1.start();
	    
	    Thread t2 = new Thread(new Runnable() {
			public void run() {
				for ( int i = 0; i < 10000; i++ ) {
					product.decrement();
				}
			}
		});
	    t2.start();
	    
	    try {
	    	t1.join();
	    	t2.join();
	    } finally {
	    	System.out.println( "product num = " + product.num );
	    }
	}
}
