package chapter01.threadmanager.demo12_threadfactory;

public class Main {

	public static void main(String[] args) {
		MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");

		Task task = new Task();
		Thread thread;
		System.out.printf("Starting the Threads\n");
		for (int i = 0; i < 5; i++) {
			// 从工厂中获取线程对象
			thread = factory.newThread(task);
			thread.start();
		}

		System.out.printf("Factory stats:\n");
		System.out.printf("%s\n", factory.getStats());

	}
	
	/**
	 	Starting the Threads
		Factory stats:
		Created thread 8 with name MyThreadFactory-Thread_0 on Sun Apr 28 17:31:02 CST 2013
		Created thread 9 with name MyThreadFactory-Thread_1 on Sun Apr 28 17:31:02 CST 2013
		Created thread 10 with name MyThreadFactory-Thread_2 on Sun Apr 28 17:31:02 CST 2013
		Created thread 11 with name MyThreadFactory-Thread_3 on Sun Apr 28 17:31:02 CST 2013
		Created thread 12 with name MyThreadFactory-Thread_4 on Sun Apr 28 17:31:02 CST 2013
	 */

}
