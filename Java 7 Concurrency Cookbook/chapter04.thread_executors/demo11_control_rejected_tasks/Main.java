package demo11_control_rejected_tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
	public static void main(String[] args) {
		RejectedTaskController controller = new RejectedTaskController();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		// 设置被拒绝的任务的处理
//		executor.setRejectedExecutionHandler(controller);
		
		// 设置ThreadPoolExecutors实现的策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

		System.out.printf("Main: Starting.\n");
		for (int i = 0; i < 3; i++) {
			Task task = new Task("Task" + i);
			executor.submit(task);
		}

		// 关闭执行者
		System.out.printf("Main: Shuting down the Executor.\n");
		executor.shutdown();
		System.out.println("执行者状态：" + executor.isShutdown());

		// 发送任务
		System.out.printf("Main: Sending another Task.\n");
		Task task = new Task("RejectedTask");
		executor.submit(task);

		System.out.printf("Main: End.\n");
	}
	
	/**
	 	Main: Starting.
		Task Task0: Starting
		Task Task2: Starting
		Task Task1: Starting
		Main: Shuting down the Executor.
		Main: Sending another Task.
		RejectedTaskController: The task java.util.concurrent.FutureTask@1bb9a58 has been rejected
		RejectedTaskController: java.util.concurrent.ThreadPoolExecutor@1922f46[Shutting down, pool size = 3, active threads = 3, queued tasks = 0, completed tasks = 0]
		Task Task0: ReportGenerator: Generating a report during 7 seconds
		Task Task1: ReportGenerator: Generating a report during 8 seconds
		Task Task2: ReportGenerator: Generating a report during 4 seconds
		RejectedTaskController: Terminating: true
		RejectedTaksController: Terminated: false
		Main: End.
		Task Task2: Ending
		Task Task0: Ending
		Task Task1: Ending
	 */
}
