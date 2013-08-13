package demo02.join_results_task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		// 生成一个10行和每行有100个单词的文档,书中是100行和每行1000个单词,这里是为了更快的看到结果
		DocumentMock mock = new DocumentMock();
		String[][] document = mock.generateDocument(10, 100, "the");

		DocumentTask task = new DocumentTask(document, 0, 10, "the");
		ForkJoinPool pool = new ForkJoinPool();

		pool.execute(task);

		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
			System.out.printf("******************************************\n");

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} while (!task.isDone());

		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			System.out.printf("Main: The word appears %d in the document", task.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 	DocumentMock: The word appears 103 times in the document.
		******************************************
		Main: Parallelism: 2
		Main: Active Threads: 1
		Main: Task Count: 0
		Main: Steal Count: 0
		******************************************
		******************************************
		Main: Parallelism: 2
		Main: Active Threads: 2
		Main: Task Count: 8
		Main: Steal Count: 0
		******************************************
		Main: The word appears 103 in the document
	 */
}
