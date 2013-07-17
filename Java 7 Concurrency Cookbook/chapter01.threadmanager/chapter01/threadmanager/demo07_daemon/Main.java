package chapter01.threadmanager.demo07_daemon;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) {
		Deque<Event> deque = new ArrayDeque<Event>();

		// WriterTask线程
		WriterTask writer = new WriterTask(deque);
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(writer);
			thread.start();
		}

		// CleanerTask线程
		CleanerTask cleaner = new CleanerTask(deque);
		cleaner.start();
	}
}
