package demo03.blocking_thread_safe_orderby_priority;

/**
 * 实现Comparable接口
 */
public class Event implements Comparable<Event> {
	private int thread;

	// 优先级
	private int priority;

	public Event(int thread, int priority) {
		this.thread = thread;
		this.priority = priority;
	}

	// 进行比较
	@Override
	public int compareTo(Event e) {
		if (this.priority > e.getPriority()) {
			return -1;
		} else if (this.priority < e.getPriority()) {
			return 1;
		} else {
			return 0;
		}
	}

	public int getThread() {
		return thread;
	}

	public int getPriority() {
		return priority;
	}

}
