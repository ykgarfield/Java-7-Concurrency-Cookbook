package demo08.custom_lock;

import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		MyLock lock = new MyLock();

		for (int i = 0; i < 10; i++) {
			Task task = new Task("Task-" + i, lock);
			Thread thread = new Thread(task);
			thread.start();
		}

		// main线程也尝试获得锁
		boolean value;
		do {
			try {
				value = lock.tryLock(1, TimeUnit.SECONDS);
				if (!value) {
					System.out.printf("Main: Trying to get the Lock\n");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				value = false;
			}
		} while (!value);
		
		System.out.printf("Main: Got the lock\n");
		lock.unlock();

		System.out.printf("Main: End of the program\n");
	}

}
