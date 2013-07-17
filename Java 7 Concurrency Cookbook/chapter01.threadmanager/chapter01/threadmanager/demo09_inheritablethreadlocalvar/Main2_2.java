package chapter01.threadmanager.demo09_inheritablethreadlocalvar;

/**
	注意是值传递, 没有引用传递
 */
public class Main2_2 {

	// InheritableThreadLocal
	private static InheritableThreadLocal<StringBuffer> holder = new InheritableThreadLocal<StringBuffer>() {
		@Override
		protected StringBuffer initialValue() {
			return new StringBuffer("aaa");
		};
	};

	public static void main(String[] args) {
		System.out.println("begin=" + holder.get());					// aaa
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread1-begin=" + holder.get());
				
//				holder.set(new StringBuffer("vvvvvvvvvvvvv"));	// 不会影响主线程中的值
				holder.set(holder.get().append(", hello"));		// 会影响主线程中的值
				
				System.out.println("thread1-end=" + holder.get());	
			}
		}).start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 虽然a线程修改了holder的值, 但是并不会影响主线程(父线程)
		System.out.println("end=" + holder.get());						// aaa
	}
	
	/**
	    holder.set(new StringBuffer("vvvvvvvvvvvvv"));的运行结果：
	 	begin=aaa
		thread-begin=aaa
		thread-end=vvvvvvvvvvvvv
		end=aaa
		
		holder.set(holder.get().append(", hello"));	的运行结果：
		begin=aaa
		thread1-begin=aaa
		thread1-end=aaa, hello
		end=aaa, hello
		
		可以看到使用第二种方式主线程中的值被改变了
	 */
}
