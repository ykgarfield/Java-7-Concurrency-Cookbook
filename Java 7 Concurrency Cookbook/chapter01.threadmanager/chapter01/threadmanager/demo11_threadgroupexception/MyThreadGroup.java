package chapter01.threadmanager.demo11_threadgroupexception;

// 继承ThreadGroup
public class MyThreadGroup extends ThreadGroup {

	public MyThreadGroup(String name) {
		super(name);
	}

	// 处理未捕获的异常
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("The thread %s has thrown an Exception\n", t.getId());
		e.printStackTrace(System.out);
		System.out.printf("Terminating the rest of the Threads\n");
		// 中断线程组中其余的线程
		interrupt();
	}
}
