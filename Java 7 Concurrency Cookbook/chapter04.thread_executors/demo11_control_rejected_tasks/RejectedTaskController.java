package demo11_control_rejected_tasks;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

// 处理被拒绝的任务.实现了RejectedExecutionHandler接口
// 在使用shutdown()方法后将被调用(如果有多个,则会调用多次)
public class RejectedTaskController implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.err.printf("RejectedTaskController: The task %s has been rejected\n", r.toString());
		System.err.printf("RejectedTaskController: %s\n", executor.toString());
		System.err.printf("RejectedTaskController: Terminating: %s\n", executor.isTerminating());
		System.err.printf("RejectedTaksController: Terminated: %s\n", executor.isTerminated());
	}

}
