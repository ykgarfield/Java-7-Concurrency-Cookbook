package demo09.priority_transfer_queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyPriorityTransferQueue<E> extends PriorityBlockingQueue<E> implements TransferQueue<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 等待的消费者的数目
	private AtomicInteger counter;

	//
	private LinkedBlockingQueue<E> transfered;

	private ReentrantLock lock;

	public MyPriorityTransferQueue() {
		counter = new AtomicInteger(0);
		lock = new ReentrantLock();
		transfered = new LinkedBlockingQueue<>();
	}

	@Override
	public boolean tryTransfer(E e) {
		lock.lock();
		
		boolean value;
		if (counter.get() == 0) {
			value = false;
		} else {
			put(e);
			value = true;
		}
		
		lock.unlock();
		
		return value;
	}

	@Override
	public void transfer(E e) throws InterruptedException {
		lock.lock();
		
		if (counter.get() != 0) {
			put(e);
			lock.unlock();
		} else {
			transfer(e);
			lock.unlock();
			
			synchronized (e) {
				e.wait();
			}
		}
	}

	@Override
	public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public boolean hasWaitingConsumer() {
		return false;
	}

	@Override
	public int getWaitingConsumerCount() {
		return 0;
	}

}
