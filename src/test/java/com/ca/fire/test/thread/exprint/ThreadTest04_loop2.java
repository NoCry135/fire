package com.ca.fire.test.thread.exprint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Print1 {
	
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	public int flg = 1;
	
	public void print5(int loop) {
		lock.lock();
		try {
			while ( flg != 1 ) {
				try {
					condition1.await();;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			for (int i = 1; i <= 5; i++) {
				System.out.println( Thread.currentThread().getName() + "第"+loop+"轮" +"打印 ：" + i );
			}
			flg = 2;
			condition2.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void print10(int loop) {
		lock.lock();
		try {
			while ( flg != 2 ) {
				try {
					condition2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			for (int i = 1; i <= 10; i++) {
				System.out.println( Thread.currentThread().getName() + "第"+loop+"轮" +"打印 ：" + i );
			}
			flg = 3;
			condition3.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void print15(int loop) {
		lock.lock();
		try {
			while ( flg != 3 ) {
				try {
					condition3.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			for (int i = 1; i <= 15; i++) {
				System.out.println( Thread.currentThread().getName() + "第"+loop+"轮" +"打印 ：" + i );
			}
			flg = 1;
			condition1.signal();
		} finally {
			lock.unlock();
		}
	}
}

public class ThreadTest04_loop2 {

	public static void main(String[] args) throws Exception {
		
		Print1 print = new Print1();
		
		new Thread(new Runnable(){
			public void run() {
				for ( int i = 1; i <= 10; i++ ) {
					print.print5(i);
				}
			}
		}, "第一个线程").start();
		
		new Thread(new Runnable(){
			public void run() {
				for ( int i = 1; i <= 10; i++ ) {
					print.print10(i);
				}
			}
		}, "第二个线程").start();
		
		new Thread(new Runnable(){
			public void run() {
				for ( int i = 1; i <= 10; i++ ) {
					print.print15(i);
				}
			}
		}, "第三个线程").start();
		
	}
}
