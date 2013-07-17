package chapter01.threadmanager.demo07_daemon;

import java.util.Date;
import java.util.Deque;

// 后台线程
public class CleanerTask extends Thread {
	private Deque<Event> deque;

	public CleanerTask(Deque<Event> deque) {
		this.deque = deque;
		// 设置为后台线程
		setDaemon(true);
	}

	@Override
	public void run() {
		while (true) {
			Date date = new Date();
			clean(date);
		}
	}

	private void clean(Date date) {
		long difference;
		boolean delete;

		if (deque.size() == 0) {
			return;
		}

		delete = false;
		do {
			Event e = deque.getLast();
			difference = date.getTime() - e.getDate().getTime();
			if (difference > 10000) {
				System.out.printf("%s Cleaner: %s\n", String.valueOf(new Date()), e.getEvent());
				// 书中的例子为：
				// System.out.printf("Cleaner: %s\n",e.getEvent());
				deque.removeLast();
				delete = true;
			}
		} while (difference > 10000);

		if (delete) {
			System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
		}
	}
}
