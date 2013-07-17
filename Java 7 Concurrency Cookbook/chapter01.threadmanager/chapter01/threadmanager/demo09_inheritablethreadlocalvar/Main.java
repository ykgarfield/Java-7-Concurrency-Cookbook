package chapter01.threadmanager.demo09_inheritablethreadlocalvar;

/**
	InheritableThreadLocal的使用
	在这个示例中, 父线程：主线程(Main)   子线程：匿名线程
	http://www.blogjava.net/stone2083/archive/2010/07/23/326894.html
 */
public class Main {
	// 无论t1定义在这里还是main()里,Child-1的值都为null
	static final ThreadLocal<String> t1 = new ThreadLocal<String>();
	
	public static void main(String[] args) {
		// 使用ThreadLocal,父子线程之间,不共享value
//		final ThreadLocal<String> t1 = new ThreadLocal<String>();
		t1.set("ThreadLocal-VAL");
		System.out.println("Main-1:" + t1.get());
		// 主线程产生的子线程(匿名线程)
		new Thread() {
			@Override
			public void run() {
				// 无法取得值
				System.out.println("Child-1:" + t1.get());	// null
			}
		}.start();
		
		// 无法取得t1的值
		Sub sub = new Sub(t1);
		sub.start();	// null
		
		
		// 使用InheritableThreadLocal,父线程Value可让子线程共享
		final ThreadLocal<String> itl = new InheritableThreadLocal<String>();
		itl.set("InheritableThreadLocal-VAL");
		System.out.println("Main-2:" + itl.get());
		// 主线程产生的子线程(匿名线程)
		new Thread() {
			@Override
			public void run() {
				System.out.println("Child-2:" + itl.get());	// InheritableThreadLocal-VAL
			}
		}.start();
		
		Sub sub2 = new Sub(itl);
		sub2.start();
	}
	
	
	/**
		Main-1:ThreadLocal-VAL
		Child-1:null
		Main-2:InheritableThreadLocal-VAL
		Sub Thread :null
		Child-2:InheritableThreadLocal-VAL
		Sub Thread :InheritableThreadLocal-VAL
	*/
}

class Sub extends Thread {
	ThreadLocal<String> t1;
	public Sub(ThreadLocal<String> t1) {
		this.t1 = t1;
	}
	
	@Override
	public void run() {
		System.out.println("Sub Thread :" + t1.get());
	}
}

