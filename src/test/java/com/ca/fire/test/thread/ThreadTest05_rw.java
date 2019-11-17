package com.ca.fire.test.thread;

class RedSpider {
	
	private String value = "";
	
	public synchronized void write ( String  value ) {
		this.value = value;
		System.out.println( "教师端输入的数据 =" + value );
	}
	
	public synchronized void read() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println( "客户端" + Thread.currentThread().getName() +"输出的数据 =" + value );
	}
}

public class ThreadTest05_rw {

	public static void main(String[] args) throws Exception {
		
		RedSpider rs = new RedSpider();
		// 客户端读取时，教师端不应该能够写入
		// 教师端写入的时候，客户端不应该读取
		// 写和读不应该并行，写和写也不应该并行，读和读可以并行。

		
		for (int i = 1; i<= 10;i++ ) {
			new Thread(new Runnable(){
				@Override
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
