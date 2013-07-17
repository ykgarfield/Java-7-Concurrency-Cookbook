package demo01.semaphore_access_a_res;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 打印队列,使用Semaphore控制访问
 */
public class PrintQueue {
	private final Semaphore semaphore;
	private final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");

	public PrintQueue() {
//		semaphore = new Semaphore(1);
		semaphore = new Semaphore(2);
	}

	public void printJob(Object document) {
		String name = Thread.currentThread().getName();
		try {
			// 获得信号量的访问.如果有其它任务打印
			// 这个线程将休眠直到获得信号量的访问
			// 有点类似于ReadLock,如果信号量 > 1,那么可以有多个线程同时获得信号量
			semaphore.acquire();
			
			System.out.println(name + " 获得信号量  at : " + sdf.format(new Date()));

			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", name,
					duration);

			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放信号量.如果有其它的线程等待信号量
			// JVM选择其中一个,让其获得访问
			
			System.out.println(name + " 释放信号量  at : " + sdf.format(new Date()) + "\r\n");
			semaphore.release();
		}
	}
}
