package demo01.customer_threadpoolexecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		MyExecutor myExecutor = new MyExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());

		List<Future<String>> results = new ArrayList<>();

		// 创建和提交10个任务
		for (int i = 0; i < 10; i++) {
			SleepTwoSecondsTask task = new SleepTwoSecondsTask();
			Future<String> result = myExecutor.submit(task);
			results.add(result);
		}

		// 取得前5个任务的执行结果
		for (int i = 0; i < 5; i++) {
			try {
				String result = results.get(i).get();
				System.out.printf("Main: Result for Task %d : %s\n", i, result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		// 关闭执行者
		myExecutor.shutdown();

		// 取得后5个任务的执行结果
		for (int i = 5; i < 10; i++) {
			try {
				String result = results.get(i).get();
				System.out.printf("Main: Result for Task %d : %s\n", i, result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		try {
			// 等待执行者的结束
			myExecutor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: End of the program.\n");
	}
}
