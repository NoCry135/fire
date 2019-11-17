package com.ca.fire.test.thread;

import java.util.Random;

class CompareAndSwap {
	public int value = 0;
	
	public synchronized int get() {
		return value;
	}
	
	public synchronized boolean compareAndSwap( int expect, int update ) {
		if ( value == expect ) {
			value = update;
			return true;
		}
		return false;
	}
}

public class ThreadTest03_volatile_cas {

	public static void main(String[] args) throws Exception {
		CompareAndSwap cas = new CompareAndSwap();
		
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable(){
				@Override
				public void run() {
					System.out.println(cas.compareAndSwap(cas.value, new Random().nextInt()));
				}
			}).start();
		}
	}
}
