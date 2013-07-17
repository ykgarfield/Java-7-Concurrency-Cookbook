package demo04_lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟打印队列
 * sleep误差问题：http://www.javamex.com/tutorials/threads/sleep_issues.shtml
 */
public class PrintQueue {
	// 控制访问队列的锁
	private final Lock queueLock = new ReentrantLock();
//	private final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");	// 获得毫秒级
	private final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS.sss");	// 获得纳秒级

	public void printJob(Object document) {
		// 得到控制锁的访问
		queueLock.lock();
//		System.out.println("获得锁  at : " + new Date());
		
		System.out.println("获得锁  at : " + sdf.format(new Date()));

		try {
			long duration = (long) (Math.random() * 10000);
//			System.out.println((duration / 1000));
			System.out.printf("%s: PrintQueue: Printing a Job during %d seconds, sleep at %s \n", Thread.currentThread().getName(),
					(duration / 1000), sdf.format(new Date()));
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
//			System.out.println("释放锁  at : " + new Date() + "\r\n");
			
			System.out.println("释放锁  at : " + sdf.format(new Date()) + "\r\n");

			queueLock.unlock();		// 释放锁
		}
	}
}
