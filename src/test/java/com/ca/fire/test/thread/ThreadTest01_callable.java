package com.ca.fire.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MyRunnable implements Runnable {
	public void run() {
		System.out.println("xxxxxx");
	}
}

class MyCallable implements Callable<Integer> {

	public Integer call() throws Exception {
		int result = 0;
		for (int i = 1; i <= 100; i++) {
			result += i;
			System.out.println( "result = " + result );
		}
		return result;
	}

}

public class ThreadTest01_callable {

	public static void main(String[] args) throws Exception {

		//Runnable run = new MyRunnable();
		//new Thread(run).start();
		
//		Callable<Integer> call = new MyCallable();
//		//new Thread(call).start();
//		FutureTask task = new FutureTask(call);
//		new Thread(task).start();
//		// FutureTask 表示执行任务，其实就是一个线程逻辑，只不过逻辑需要有结果。
//		// 这个结果需要在后续的处理中得到，如果结果没有得到，那么线程需要暂停来获取结果
//		//System.out.println( "final result = " + task.get() );
//
//		System.out.println( "main执行完毕" );
		
		Callable<Integer> call = new MyCallable();
		FutureTask<Integer> task = new FutureTask(call);
		new Thread(task).start();
		
		int result = 0;
		for ( int i = 1; i <= 50; i++ ) {
			result += i;
		}
		System.out.println( result + task.get());
		
	}

}
