package demo09.multithreadedtc;

import java.util.concurrent.LinkedTransferQueue;

import edu.umd.cs.mtc.MultithreadedTestCase;

/**
 * 这个类实现了LinkedTransferQueue的测试. 
 * 它有两个消费者和一个生产者.首先,第一个消费者达到,然后第二个消费者达到.
 * 最后,生产者将两个String放到缓冲区中.
 */
public class ProducerConsumerTest extends MultithreadedTestCase {
	// 声明缓冲区,由生产者和消费者共享
	private LinkedTransferQueue<String> queue;

	// 创建缓冲区
	@Override
	public void initialize() {
		super.initialize();
		queue = new LinkedTransferQueue<>();
		System.out.printf("Test: The test has been initialized\n");
	}

	/**
	 * 第一个消费者,它只取出一个String
	 */
	public void thread1() throws InterruptedException {
		System.out.println("thread1...");
		String ret = queue.take();
		System.out.printf("Thread 1: %s\n", ret);
	}

	/**
	 * 第二个消费者,它等待第一个标记发生,当第一个消费者达到. 然后,取出一个String
	 */
	public void thread2() throws InterruptedException {
		System.out.println("thread2...");
		super.waitForTick(1);
		String ret = queue.take();
		System.out.printf("Thread 2: %s\n", ret);
	}

	/**
	 * 生产者.它等待第一个标记发生,第一个消费者达到.
	 * 然后,等待第二个标记发生,第二个消费者达到.
	 * 最后,将两个String放到缓冲区.
	 */
	public void thread3() {
		waitForTick(1);
		waitForTick(2);
		queue.put("Event 1");
		queue.put("Event 2");
		System.out.printf("Thread 3: Inserted two elements\n");
	}

	@Override
	public void finish() {
		super.finish();
		System.out.printf("Test: End\n");
		assertEquals(true, queue.size() == 0);
		System.out.printf("Test: Result: The queue is empty\n");
	}
}
