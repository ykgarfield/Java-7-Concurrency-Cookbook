package chapter01.threadmanager.demo09_threadlocalvar;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable {
	// ThreadLocal
	private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
		@Override
		protected Date initialValue() {
			return new Date();
		}
	};

	@Override
	public void run() {
		System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate.get());
		try {
			TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate.get());
	}

}
