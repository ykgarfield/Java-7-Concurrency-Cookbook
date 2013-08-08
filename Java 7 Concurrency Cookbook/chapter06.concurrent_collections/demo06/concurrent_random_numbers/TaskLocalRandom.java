package demo06.concurrent_random_numbers;

import java.util.concurrent.ThreadLocalRandom;

public class TaskLocalRandom implements Runnable {
	
	/**
	 * 为这个任务初始化随机数生成器
	 */
	public TaskLocalRandom() {
		ThreadLocalRandom.current();
	}

	/**
	 * 生成10个随机数, 输出到屏幕上
	 */
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s: %d\n", name, ThreadLocalRandom.current().nextInt(10));
		}
	}

}
