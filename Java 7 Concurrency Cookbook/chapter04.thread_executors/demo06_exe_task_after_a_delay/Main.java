package demo06_exe_task_after_a_delay;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		// 创建ScheduledExecutorService
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		System.out.printf("Main: Starting at: %s\n", new Date());

		// 在指定的延迟时间后发送任务到执行者
		for (int i = 0; i < 5; i++) {
			Task task = new Task("Task-" + i);
			// schedule(Callable<V> callable, long delay, TimeUnit unit)
			executor.schedule(task, i + 1, TimeUnit.SECONDS);
		}

		executor.shutdown();

		try {
			// 等待执行者结束,会阻塞
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Core: Ends at: %s\n", new Date());
	}
}
