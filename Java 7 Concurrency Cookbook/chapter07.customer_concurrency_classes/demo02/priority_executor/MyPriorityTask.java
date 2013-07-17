package demo02.priority_executor;

import java.util.concurrent.TimeUnit;

class MyPriorityTask implements Runnable, Comparable<MyPriorityTask> {
	// 任务的优先级
	private int priority;

	private String name;

	public MyPriorityTask(String name, int priority) {
		this.name = name;
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public int compareTo(MyPriorityTask o) {
		if (this.getPriority() < o.getPriority()) {
			return 1;
		}

		if (this.getPriority() > o.getPriority()) {
			return -1;
		}

		return 0;
	}

	@Override
	public void run() {
		System.out.printf("MyPriorityTask: %s Priority : %d\n", name, priority);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
