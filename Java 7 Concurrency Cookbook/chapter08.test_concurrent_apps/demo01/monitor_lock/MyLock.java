package demo01.monitor_lock;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock extends ReentrantLock {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 返回控制锁的线程的名称
	 */
	public String getOwnerName() {
		if (this.getOwner() == null) {
			return "None";
		}
		
		return this.getOwner().getName();
	}

	
	/**
	 * 锁队列中线程的列表
	 */
	public Collection<Thread> getThreads() {
		return this.getQueuedThreads();
	}
}
