package test.java.util.concurrent;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorService_shutdownNow {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		
		for (int i = 0; i < 5; i++) {
			Task task = new Task("Task-" + i);
			executor.submit(task);
		}
		
		List<Runnable> list = executor.shutdownNow();
		System.out.println(list);
		
		/*
		 	[java.util.concurrent.FutureTask@55d93d, java.util.concurrent.FutureTask@296f76, java.util.concurrent.FutureTask@54c4ad]
			Task-1 running at : Tue Jun 18 17:12:23 CST 2013
			Task-0 running at : Tue Jun 18 17:12:23 CST 2013
			Task-1 running at : Tue Jun 18 17:12:23 CST 2013
			Task-1 interrupt : java.lang.InterruptedException: sleep interrupted
			Task-0 interrupt : java.lang.InterruptedException: sleep interrupted
			Task-0 running at : Tue Jun 18 17:12:23 CST 2013
			Task-1 running at : Tue Jun 18 17:12:24 CST 2013
			Task-0 running at : Tue Jun 18 17:12:24 CST 2013
		 */
	}
	
	static class Task implements Runnable {
		private String name;
		
		public Task(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			for(int i = 0; i < 3; i++) {
				System.out.println(name + " running at : " + new Date());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.err.println(name + " interrupt : " + e);
				}
			}
		}

		
		@Override
		public String toString() {
			return this.name;
		}
	}
}

