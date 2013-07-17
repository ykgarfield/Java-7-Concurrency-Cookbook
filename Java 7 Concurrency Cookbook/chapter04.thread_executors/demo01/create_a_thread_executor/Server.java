package demo01.create_a_thread_executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 模拟一个服务器,比如web服务器,接收请求 使用ThreadPoolExecutor去执行这些请求
 */
public class Server {
	private ThreadPoolExecutor executor;

	public Server() {
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}

	public void executeTask(Task task) {
		System.out.printf("Server: A new task has arrived\n");
		executor.execute(task);
		// 实际的线程数目
		System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
		// 正在执行的任务的数目
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
	}
	
	public void endServer() {
		executor.shutdown();
	}
}
