package chapter01.threadmanager.demo09_threadlocalvar;

import java.util.concurrent.TimeUnit;

public class SafeMain {

	public static void main(String[] args) {
		SafeTask task = new SafeTask();

		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(task);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			thread.start();
		}
	}
	
	/**
	 	Starting Thread: 9 : Sun Apr 28 00:00:27 CST 2013
		Starting Thread: 10 : Sun Apr 28 00:00:29 CST 2013
		Thread Finished: 10 : Sun Apr 28 00:00:29 CST 2013
		Starting Thread: 11 : Sun Apr 28 00:00:31 CST 2013
		Thread Finished: 9 : Sun Apr 28 00:00:27 CST 2013
		Thread Finished: 11 : Sun Apr 28 00:00:31 CST 2013
	 */

}
