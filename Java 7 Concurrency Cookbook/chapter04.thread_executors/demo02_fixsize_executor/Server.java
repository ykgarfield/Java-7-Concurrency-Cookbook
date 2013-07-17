package demo02_fixsize_executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 模拟一个服务器,比如web服务器,接收请求 使用ThreadPoolExecutor去执行这些请求
 */
public class Server {
	private ThreadPoolExecutor executor;

	public Server() {
		// 创建固定大小的线程池
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
	}

	public void executeTask(Task task) {
		System.out.printf("Server: A new task has arrived\n");
		executor.execute(task);
		System.out.printf("Server: Pool Size: %d\n",executor.getPoolSize());
		System.out.printf("Server: Active Count: %d\n",executor.getActiveCount());
		// 需要执行的任务的数目
		System.out.printf("Server: Task Count: %d\n",executor.getTaskCount());
		System.out.printf("Server: Completed Tasks: %d\n",executor.getCompletedTaskCount());
	}
	
	public void endServer() {
		executor.shutdown();
	}
}
