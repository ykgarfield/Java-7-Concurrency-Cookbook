package chapter01.threadmanager.demo09_inheritablethreadlocalvar;

/**
	ThreadLocal有个缺陷, 在子线程里无法访问父线程的变量, 
	InheritableThreadLocal 解决了这个问题, 自动会把父线程的变量传递个子线程, 
	子线程只能用, 修改了不会影响父线程的东西, 这里仍然需要注意并发实现~ 
	与Main2_2一起测试做比较
	
	http://czh.iteye.com/blog/724206
 */
public class Main2 {

	// InheritableThreadLocal
	private final static InheritableThreadLocal<String> holder = new InheritableThreadLocal<String>();

	public static void main(String[] args) {
		holder.set("aaa");
		System.out.println("begin=" + holder.get());					// aaa
		// 子线程a
		Thread a = new Thread() {
			@Override
			public void run() {
				System.out.println("thread-begin=" + holder.get());		// aaa
				// 修改值
				holder.set("vvvvvvvvvvvvv");
				// 只会在这里起作用,而不会影响父线程
				System.out.println("thread-end=" + holder.get());		// vvvvvvvvvvvvv
			}
		};
		a.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 虽然a线程修改了holder的值, 但是并不会影响主线程(父线程)
		System.out.println("end=" + holder.get());						// aaa
	}
	
	/**
	 	begin=aaa
		thread-begin=aaa
		thread-end=vvvvvvvvvvvvv
		end=aaa
	 */
}
