package demo05.thread_safe_navigable_maps;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Main {
	public static void main(String[] args) {
		ConcurrentSkipListMap<String, Contact> map = new ConcurrentSkipListMap<>();

		Thread threads[] = new Thread[25];
		int counter = 0;

		// 执行25个任务
		for (char i = 'A'; i < 'Z'; i++) {
			Task task = new Task(map, String.valueOf(i));
			threads[counter] = new Thread(task);
			threads[counter].start();
			counter++;
		}

		// 等待线程结束
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.printf("Main: Size of the map: %d\n", map.size());

		// 输出第一个元素
		Map.Entry<String, Contact> element;
		Contact contact;

		element = map.firstEntry();
		contact = element.getValue();
		System.out.printf("Main: First Entry: %s: %s\n", contact.getName(), contact.getPhone());

		// 输出最后一个元素
		element = map.lastEntry();
		contact = element.getValue();
		System.out.printf("Main: Last Entry: %s: %s\n", contact.getName(), contact.getPhone());

		// 输出一个map的子集合
		System.out.printf("Main: Submap from A1996 to B1002: \n");
		ConcurrentNavigableMap<String, Contact> submap = map.subMap("A1996", "B1002");
		do {
			element = submap.pollFirstEntry();
			if (element != null) {
				contact = element.getValue();
				System.out.printf("%s: %s\n", contact.getName(), contact.getPhone());
			}
		} while (element != null);
	}
}
