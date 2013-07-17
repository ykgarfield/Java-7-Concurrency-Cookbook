package chapter01.threadmanager.demo12_threadfactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

// 实现ThreadFactory接口
public class MyThreadFactory implements ThreadFactory {

	// 保存必要数据的属性
	private int counter;
	private String name;
	private List<String> stats;

	// 构造
	public MyThreadFactory(String name) {
		counter = 0;
		this.name = name;
		stats = new ArrayList<String>();
	}

	// 创建一个线程
	@Override
	public Thread newThread(Runnable r) {
		// 创建线程对象
		Thread t = new Thread(r, name + "-Thread_" + counter);
		counter++;

		// 统计
		stats.add(String.format("Created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
		return t;
	}

	// ThreadFactory的统计信息
	public String getStats() {
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = stats.iterator();
		
		while(it.hasNext()) {
			buffer.append(it.next());
		}
		
		return buffer.toString();
	}
}
