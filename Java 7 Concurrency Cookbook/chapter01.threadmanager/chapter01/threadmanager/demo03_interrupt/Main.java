package chapter01.threadmanager.demo03_interrupt;

import java.util.concurrent.TimeUnit;

// 线程中断
public class Main {

	public static void main(String[] args) throws InterruptedException {
		Thread task = new PrimeGenerator();
		task.start();	// 运行
		
		// 等待5秒
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 中断PrimeGenerator线程, 但是并不会终止线程的执行
		task.interrupt();
		
		Thread.sleep(5000);
		System.out.println(task.getState());	// TERMINATED
		task.interrupt();	// 这里如果线程终止了,再次调用interrupt()是不会抛出异常的
	}

}

