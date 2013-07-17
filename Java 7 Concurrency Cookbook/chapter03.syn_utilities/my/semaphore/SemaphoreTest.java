package my.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * http://ikoo.info/posts/java-thread-notes-semaphore/
 */
public class SemaphoreTest {
	public static void main(String[] args) {
		Runnable limitedCall = new Runnable() {
			final Random rand = new Random();
			final Semaphore available = new Semaphore(3);
			int count = 0;

			public void run() {
				int time = rand.nextInt(15);
				int num = count++;

				try {
					// 获得信号量
					available.acquire();

					System.out.println("Executing " + "long-running action for " + time + " seconds... #" + num);

					Thread.sleep(time * 1000);

					System.out.println("Done with #" + num + "!");

					// 释放
					available.release();
				} catch (InterruptedException intEx) {
					intEx.printStackTrace();
				}
			}
		};

		for (int i = 0; i < 10; i++)
			new Thread(limitedCall).start();
	}
}
