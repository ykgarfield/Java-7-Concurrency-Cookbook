package demo01.no_blocking_thread_safe_lists;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 增加10000个元素到ConcurrentListDeque
 */
public class AddTask implements Runnable {
	private ConcurrentLinkedDeque<String> list;

	public AddTask(ConcurrentLinkedDeque<String> list) {
		this.list = list;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 10000; i++) {
			list.add(name + ": Element " + i);
		}
	}

}
