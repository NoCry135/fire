package com.ca.fire.test.thread.unit02;
/**
 * ����һ������
 * @author liyuting
 *
 */
public class TestDeadLock {
	
	public static void main(String[] args) {
		
		DeadDemo d1 = new DeadDemo(true);
		DeadDemo d2 = new DeadDemo(false);
		
		d1.start();
		d2.start();
	}

}

class DeadDemo extends Thread{
	static MrLiSi m = new MrLiSi();
	static BadBoy b = new BadBoy();
	public DeadDemo(boolean flag){
		this.flag=flag;
	}
	
	boolean flag;
	
	@Override
	public void run() {
		
		
		while (true) {
			if (flag) {

				synchronized (m) {

					m.say();
					synchronized (b) {
						m.get();
					}
				}

			} else {
				synchronized (b) {

					b.say();
					synchronized (m) {
						b.get();
					}
				}

			}
		}
	}
	
	
	
	
}
class MrLiSi{
	public void say(){
		System.out.println("李员外说：你把小花给我，我把钱给你！！！！");
	}

	public void get(){
		System.out.println("李员外得到了小花！！1");
	}
	
}

class BadBoy{



	public void say(){
		System.out.println("坏蛋说：你把钱给我，我把小花还给你！！！！");
	}

	public void get(){
		System.out.println("坏蛋得到了钱！！1");
	}
}