package demo07_periodic_task;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		System.out.printf("Main: Starting at: %s\n", new Date());

		Task task = new Task("Task");
		// scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
		// 创建一个任务发送到执行者.将1秒延迟后将开始执行,每2秒执行一次
		// 因为Task实现的Runnable接口, 所以这里的参数使用 ? 
		ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

		// 控制线程的执行
		for (int i = 0; i < 10; i++) {
			// 下一次任务执行的时间
			System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Finish the executor
		executor.shutdown();
		System.out.printf("Main: No more tasks at: %s\n", new Date());
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Finished at: %s\n", new Date());
	}
	
	
	/**
		Main: Starting at: Wed May 15 15:32:42 CST 2013
		Main: Delay: 999
		Main: Delay: 499
		Main: Delay: 0
		Task: Executed at: Wed May 15 15:32:45 CST 2013
		Main: Delay: 1498
		Main: Delay: 998
		Main: Delay: 498
		Main: Delay: -1
		Task: Executed at: Wed May 15 15:32:47 CST 2013
		Main: Delay: 1498
		Main: Delay: 998
		Main: Delay: 498
		Task: Executed at: Wed May 15 15:32:49 CST 2013
		Main: No more tasks at: Wed May 15 15:32:49 CST 2013
		Main: Finished at: Wed May 15 15:32:54 CST 2013
	 */
}
