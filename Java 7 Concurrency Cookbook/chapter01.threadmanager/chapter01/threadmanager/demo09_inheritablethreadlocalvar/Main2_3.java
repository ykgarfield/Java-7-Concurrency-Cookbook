package chapter01.threadmanager.demo09_inheritablethreadlocalvar;

// childValue()的用法
public class Main2_3 {

	private final static InheritableThreadLocal<String> holder = new InheritableThreadLocal<String>() {
		// 覆盖childValue()方法, 这样在子线程中使用get()方法取得的值就是从这里获取
		@Override
		protected String childValue(String value) {
			return "Child created from " + value;
		};
	};

	public static void main(String[] args) {
		// 主线程
		holder.set("Lily");
		System.out.println("main begin=" + holder.get());			// Lily		
		
		// 子线程a
		Thread a = new Thread() {
			@Override
			public void run() {
				// 子线程中的值是从childValue()中取得
				System.out.println("thread-begin=" + holder.get());	// Child created from Lily
				holder.set("Lucy");
				System.out.println("thread-end=" + holder.get());	// Lucy
			}
		};
		a.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main end=" + holder.get());				// Lily			
	}
	
	/**
	 	main begin=Lily
		thread-begin=Child created from Lily
		thread-end=Lucy
		main end=Lily
	 */
}
