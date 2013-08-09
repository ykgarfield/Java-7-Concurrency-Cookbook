package demo08.custom_lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 继承了AbstractQueueSynchronizer类去实现一个基本的锁,它使用一个AtomicInteger变量区存储锁的状态.
 * 它也存储当前获取锁的线程.TryAcquire()方法和tryRelease()方法是锁的实现的起点.
 */
public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 存储lock的状态的属性.0-free, 1-busy
	 */
	private AtomicInteger state;
	
	public MyAbstractQueuedSynchronizer() {
		state = new AtomicInteger(0);
	}
	
	@Override
	public boolean tryAcquire(int arg) {
		return state.compareAndSet(0, 1);
	}
	
	@Override
	public boolean tryRelease(int arg) {
		return state.compareAndSet(1, 0);
	}
}

