package chapter01.threadmanager.demo09_inheritablethreadlocalvar;

// 可以在在线程内实现一个局部变量，可以在线程的任何地方来访问，能够减少参数的传递
public class ThreadLocalTest {
	private static ThreadLocal<String> tl_1 = new ThreadLocal<String>() {
		// 返回本线程的名字，变量1
		@Override
		protected String initialValue() {
			return "Thread name 1 : " + Thread.currentThread().getName();
		}
	};
	private static ThreadLocal<String> tl_2 = new ThreadLocal<String>() {
		// 返回本线程的名字，变量2
		@Override
		protected String initialValue() {
			return "Thread name 2 : " + Thread.currentThread().getName();
		}
	};

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					new ThreadLocalTest().prt();
				}
			}).start();
		}
	}

	// 打印本线程的名字
	public void prt() {
		System.out.println(tl_1.get());
		System.out.println(tl_2.get());
	}
}
