package demo01.monitor_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Task implements Runnable {
	private Lock lock;

	public Task(Lock lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			// »ñÈ¡Ëø
			lock.lock();

			System.out.printf("%s: Get the  Lock.\n", Thread.currentThread().getName());

			try {
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.printf("%s: Free the Lock.\n", Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// ÊÍ·ÅËø
				lock.unlock();
			}
		}
	}

}
