package demo04.throw_exception;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class Task extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int array[];

	private int start, end;

	public Task(int array[], int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		System.out.printf("Task: Start from %d to %d\n", start, end);
		if (end - start < 10) {
			if ((3 > start) && (3 < end)) {
				// Å×³öÒì³£
				throw new RuntimeException("This task throws an Exception: Task from  " + start + " to " + end);
			}

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			int mid = (end + start) / 2;
			Task task1 = new Task(array, start, mid);
			Task task2 = new Task(array, mid, end);
			invokeAll(task1, task2);
			System.out.printf("Task: Result form %d to %d: %d\n", start, mid, task1.join());
			System.out.printf("Task: Result form %d to %d: %d\n", mid, end, task2.join());
		}

		System.out.printf("Task: End form %d to %d\n", start, end);
		return new Integer(0);
	}

}
