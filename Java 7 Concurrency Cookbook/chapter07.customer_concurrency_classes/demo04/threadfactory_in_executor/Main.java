package demo04.threadfactory_in_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		MyThreadFactory threadFactory = new MyThreadFactory("MyThreadFactory");

		// 使用线程工厂创建执行者
		ExecutorService executor = Executors.newCachedThreadPool(threadFactory);

		MyTask task = new MyTask();
		executor.submit(task);

		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);

		System.out.printf("Main: End of the program.\n");
	}
}
