package demo06.findbugs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Task implements Runnable {
	private ReentrantLock lock;

	public Task(ReentrantLock lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		lock.lock();

		try {
			TimeUnit.SECONDS.sleep(1);
			/*
			 * 在这里进行解锁操作会有问题.如果线程在休眠的时候被中断.锁将不会释放,
			 * 将引起其它等待这个块的线程阻塞.永远不会获得锁的控制.
			 */
			lock.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
