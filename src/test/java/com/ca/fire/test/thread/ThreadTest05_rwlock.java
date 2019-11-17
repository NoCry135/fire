package com.ca.fire.test.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class RedSpider1 {
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	private String value = "";
	
	public void write ( String  value ) {
		lock.writeLock().lock();
		try {
			this.value = value;
			System.out.println( "教师端输入的数据 =" + value );
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public void read() {
		lock.readLock().lock();
		try {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println( "客户端" + Thread.currentThread().getName() +"输出的数据 =" + value );
		} finally {
			lock.readLock().unlock();
		}
	}
}

public class ThreadTest05_rwlock {

	public static void main(String[] args) throws Exception {
		
		RedSpider1 rs = new RedSpider1();
		// 客户端读取时，教师端不应该能够写入
		// 教师端写入的时候，客户端不应该读取
		// 写和读不应该并行，写和写也不应该并行，读和读可以并行。


		
		for (int i = 1; i<= 10;i++ ) {
			new Thread(new Runnable(){
				public void run() {
					rs.read();
				}
			}, ""+i).start();
		}
		Thread.sleep(100);
		new Thread(new Runnable(){
			@Override
			public void run() {
				rs.write("Java Thread");
			}
		}).start();

	}
}
