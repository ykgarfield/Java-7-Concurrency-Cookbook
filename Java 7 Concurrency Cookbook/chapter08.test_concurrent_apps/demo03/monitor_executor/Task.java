package demo03.monitor_executor;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	// 线程休眠的毫秒数
	private long milliseconds;

	public Task(long milliseconds) {
		this.milliseconds = milliseconds;
	}

	@Override
	public void run() {
		System.out.printf("%s: Begin\n", Thread.currentThread().getName());
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: End\n", Thread.currentThread().getName());
	}

}
