package demo02.priority_executor;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		// 使用PriorityBlockingQueue作为数据结构去存储任务
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS,
				new PriorityBlockingQueue<Runnable>());

		// 发送4个任务
		for (int i = 0; i < 4; i++) {
			MyPriorityTask task = new MyPriorityTask("Task " + i, i);
			executor.execute(task);
		}

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 发送4个任务
		for (int i = 4; i < 8; i++) {
			MyPriorityTask task = new MyPriorityTask("Task " + i, i);
			executor.execute(task);
		}

		executor.shutdown();

		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: End of the program.\n");
	}
}
