package demo04_lock2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockingTest {
	final Lock lock = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException {
		ReentrantLockingTest r = new ReentrantLockingTest();

		Thread first = new Thread(r.createTask(), "FirstThread");
		Thread second = new Thread(r.createTask(), "SecondThread");
		first.start();
		second.start();
		Thread.sleep(600);
		second.interrupt();
		Thread.sleep(10000);
	}

	private Runnable createTask() {
		return new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						if (lock.tryLock(500, TimeUnit.MILLISECONDS)) 
						//if (lock.tryLock())
						//lock.lock();
						//lock.lockInterruptibly();
						{
							try {
								System.out.println("locked " + Thread.currentThread().getName());
								Thread.sleep(1000);
							} finally {
								lock.unlock();
								System.out.println("unlocked  " + Thread.currentThread().getName());
							}
							break;
						}
						else {
							System.out.println("unable to lock " + Thread.currentThread().getName());
						}
					} catch (InterruptedException e) {
						System.out.println(Thread.currentThread().getName() + " is Interrupted");
					}
				}
			}
		};
	}
}
