package demo01.create_a_thread_executor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
	// 任务开始时间
	private Date initDate;

	// 任务名称
	private String name;

	public Task(String name) {
		initDate = new Date();
		this.name = name;
	}

	@Override
	public void run() {
		System.out.printf("%s: Task %s: Created on: %s\n", Thread.currentThread().getName(), name, initDate);
		System.out.printf("%s: Task %s: Started on: %s\n", Thread.currentThread().getName(), name, new Date());

		// 休眠
		try {
			Long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Task %s: Doing a task during %d seconds\n", Thread.currentThread().getName(), name,
					duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("%s: Task %s: Finished on: %s\n", Thread.currentThread().getName(), name, new Date());
	}

}
