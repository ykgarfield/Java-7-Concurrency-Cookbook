package chapter01.threadmanager.demo10_threadgroup;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SearchTask implements Runnable {
	// 存储第一个休眠结束的线程的名称
	private Result result;

	public SearchTask(Result result) {
		this.result = result;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.printf("Thread %s: Start\n", name);
		try {
			doTask();
			// 设置线程结束的名称
			result.setName(name);
		} catch (InterruptedException e) {
			System.out.printf("Thread %s: Interrupted\n", name);
			return;
		}
		System.out.printf("Thread %s: End\n", name);
	}

	/**
	 * 模拟搜索操作
	 */
	private void doTask() throws InterruptedException {
		Random random = new Random(new Date().getTime());
		int value = (int) (random.nextDouble() * 100);
		System.out.printf("Thread %s: %d\n", Thread.currentThread().getName(), value);
		TimeUnit.SECONDS.sleep(value);	// 休眠随机时间
	}
}
