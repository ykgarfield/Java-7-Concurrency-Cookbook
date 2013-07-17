package demo03_conditions_syn_block;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventStorage {

	// 最大存储量
	private int maxSize;

	// 存储的项目
	private List<Date> storage;

	public EventStorage() {
		maxSize = 10;
		// JDK7的新泛型语法
		storage = new LinkedList<>();
	}

	// 存储一个项目
	public synchronized void set() {
		while (storage.size() == maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		storage.add(new Date());
		System.out.printf("Set: %d \n", storage.size());

		notify();
	}

	// 删除第一个项目
	public synchronized void get() {
		while (storage.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.printf("Get: %d: %s \n", storage.size(), ((LinkedList<?>) storage).poll());
		notify();
	}
}
