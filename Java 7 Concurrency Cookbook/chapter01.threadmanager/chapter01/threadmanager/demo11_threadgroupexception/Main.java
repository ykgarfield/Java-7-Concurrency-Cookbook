package chapter01.threadmanager.demo11_threadgroupexception;

public class Main {

	public static void main(String[] args) {
		// 创建线程组
		MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
		Task task = new Task();
		// 可以增大创建的线程数量,效果会更明显
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(threadGroup, task);
			t.start();
		}
	}
}
