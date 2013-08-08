package demo04.thread_safe_delayed;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class Task implements Runnable {
	private int id;

	private DelayQueue<Event> queue;

	public Task(int id, DelayQueue<Event> queue) {
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		Date now = new Date();
		Date delay = new Date();
		// 可以粗略认为每个线程之间隔1秒钟
		delay.setTime(now.getTime() + (id * 1000));

		System.out.printf("Thread %s: %s\n", id, delay);

		for (int i = 0; i < 100; i++) {
			Event event = new Event(delay);
			queue.add(event);
		}
	}

}
