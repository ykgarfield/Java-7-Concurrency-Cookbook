package demo05.thread_safe_navigable_maps;

import java.util.concurrent.ConcurrentSkipListMap;

public class Task implements Runnable {
	private ConcurrentSkipListMap<String, Contact> map;

	private String id;

	public Task(ConcurrentSkipListMap<String, Contact> map, String id) {
		this.map = map;
		this.id = id;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			Contact contact = new Contact(id, String.valueOf(i + 1000));
			map.put(id + contact.getPhone(), contact);
		}
	}

}
