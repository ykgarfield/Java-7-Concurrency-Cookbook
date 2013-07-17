package chapter01.threadmanager.demo09_threadlocalvar;

import java.util.concurrent.TimeUnit;

/**
 * 创建1个Runnable任务, 3个线程对象运行它
 */
public class UnsafeMain {
	public static void main(String[] args) {
		UnsafeTask task = new UnsafeTask();

		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(task);
			thread.start();
			try {
				// 睡眠2秒
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 	Starting Thread: 9 : Sat Apr 27 23:49:55 CST 2013
		Thread Finished: 9 : Sat Apr 27 23:49:55 CST 2013
		Starting Thread: 10 : Sat Apr 27 23:49:57 CST 2013
		Starting Thread: 11 : Sat Apr 27 23:49:59 CST 2013
		Thread Finished: 10 : Sat Apr 27 23:49:59 CST 2013
		Thread Finished: 11 : Sat Apr 27 23:49:59 CST 2013
	 */
}
