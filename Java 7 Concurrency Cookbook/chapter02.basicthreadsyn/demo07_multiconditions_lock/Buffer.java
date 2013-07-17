package demo07_multiconditions_lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者共享的数据
 */
public class Buffer {
	// 缓冲, 存储共享数据
	private LinkedList<String> buffer;

	// 缓冲的最大长度
	private int maxSize;

	// 控制访问缓冲的锁
	private ReentrantLock lock;

	// 控制缓冲是否还有一行数据和可用空间的条件
	private Condition lines;
	private Condition space;

	// 缓冲中是否还有行
	private boolean pendingLines;

	public Buffer(int maxSize) {
		// 初始化属性
		this.maxSize = maxSize;
		buffer = new LinkedList<>();
		lock = new ReentrantLock();
		lines = lock.newCondition();
		space = lock.newCondition();
		pendingLines = true;
	}

	/**
	 * 接收一个字符串参数, 尝试存储在buffer中
	 */
	public void insert(String line) {
		// 取得锁的控制
		lock.lock();

		try {
			// 缓冲区已满
			while (buffer.size() == maxSize) {
				// 注意：调用的是await()方法. 这个方法是Condition类的. 不要误写成wait(), 那是Object类的方法
				// 当然了也属于是Condition的方法,但是这里要注意这两个单词只有1个字母的区别,容易搞错
				// await()对应的是signal()、signalAll()
				// 调用await()方法会自动释放锁的控制
				space.await();	// 等待可用空间
				// 如果有另一个线程调用signal()或者signalAll()方法,将被唤醒
			}

			buffer.offer(line);
			System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(), buffer.size());
			lines.signalAll();	// 唤醒lines.wait()处
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	//
	public String get() {
		String line = null;
		lock.lock();
		try {
			while ((buffer.size() == 0) && hasPendingLines()) {
				lines.await();	// 被lines.signalAll()唤醒
			}

			if (hasPendingLines()) {
				line = buffer.poll();
				System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(), buffer.size());
				space.signalAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		return line;
	}

	/**
	 * 有生产者调用, 不会有更多的行数据
	 */
	public void setPendingLines(boolean pendingLines) {
		this.pendingLines = pendingLines;
	}

	/**
	 * 是否有更多的行数据要处理
	 */
	public boolean hasPendingLines() {
		return pendingLines || buffer.size() > 0;
	}
}
