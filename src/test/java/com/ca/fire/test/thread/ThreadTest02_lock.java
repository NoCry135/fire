package com.ca.fire.test.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
	public int num = 0;
	public synchronized void print() {
		
		try {
			while ( num != 0 ) {
				this.wait();
			}
			num++;
			this.notify();
			Thread.sleep(1000*60*60*24*7);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println( "print ..." );
	}
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void print1() {
		//lock.lockInterruptibly();
		lock.lock();
		try {
			try {
				while ( num != 0 ) {
					condition.await();;
				}
				num++;
				condition.signal();
				TimeUnit.SECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println( "print 1111 ..." );
		} finally {
			lock.unlock();
		}
	}
}

public class ThreadTest02_lock {

	public static void main(String[] args) throws Exception {
		
		ShareData data = new ShareData();
		
		new Thread(new Runnable(){
			public void run() {
				data.print1();
			}
		}).start();
		System.out.println( "main 执行完毕" );
	}

}
