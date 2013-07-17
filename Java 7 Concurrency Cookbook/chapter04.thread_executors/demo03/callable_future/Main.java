package demo03.callable_future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
	public static void main(String[] args) {
		// 固定数目的线程池
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		List<Future<Integer>> resultList = new ArrayList<>();

		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			Integer number = new Integer(random.nextInt(10));
			FactorialCalculator calcutor = new FactorialCalculator(number);
			// 提交任务
			Future<Integer> result = executor.submit(calcutor);
			resultList.add(result);
		}

		// 循环, 监控线程池的状态
		do {
			System.out.printf("Main: Number of Completed Tasks: %d\n", executor.getCompletedTaskCount());
			for (int i = 0; i < resultList.size(); i++) {
				Future<Integer> result = resultList.get(i);
				// isDone()：返回true,说明任务完成
				System.out.printf("Main: Task %d: %s\n", i, result.isDone());
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (executor.getCompletedTaskCount() < resultList.size());	// 等待任务结束

		System.out.printf("Main: Results\n");
		// 因为我们要在下面取得线程返回的结果,那么就必须要让所有的线程都执行结束了才能取得结果
		// 所以上面需要while循环来判断线程是否已经全部结束
		for (int i = 0; i < resultList.size(); i++) {
			Future<Integer> result = resultList.get(i);
			Integer number = null;
			try {
				// 取得返回的结果
				// get()方法会阻塞直到call()方法执行结束并返回结果
				number = result.get();
				
			    // 异常
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.printf("Core: Task %d: %d\n", i, number);
		}

		/*
        * 调用shutdown()方法之后,主线程就马上结束了,而线程池会继续运行直到所有任务执行完才会停止
        * 如果不调用 shutdown()方法,那么线程池会一直保持下去,以便随时添加新的任务.(shutdown()方法不会阻塞)
        */
		executor.shutdown();
	}
}
