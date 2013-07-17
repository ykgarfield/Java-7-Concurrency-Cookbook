package my.atomic;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class AtomicIntegerTest {

	@Test
	public void testAll() throws InterruptedException {
		// 设定初始值
		final AtomicInteger value = new AtomicInteger(10);
		// compareAndSet()：如果当前值 == 预期值,则以原子方式将该值设置为给定的更新值. 如果成功就返回true,否则返回false,并且不修改原值
		System.out.println(value.compareAndSet(1, 2));	// false
		System.out.println(value.get());				// 10
		System.out.println(value.compareAndSet(10, 3));	// true
		System.out.println(value.get());				// 3
		// 设置为给定值.直接修改原始值,也就是i=newValue操作
		value.set(0);

		// incrementAndGet()：以原子方式将当前值加 1. 相当于线程安全版本的++i操作
		System.out.println(value.incrementAndGet());	// 1
		// getAndAdd()：以原子方式将给定值与当前值相加.相当于线程安全版本的t=i;i+=delta;return t;操作
		System.out.println(value.getAndAdd(2));			// 1
		System.out.println(value.get());				// 3
		System.out.println(value.getAndSet(5));			// 3
		System.out.println(value.get());				// 5
		

		final int threadSize = 10;
		Thread[] ts = new Thread[threadSize];
		for (int i = 0; i < threadSize; i++) {
			ts[i] = new Thread() {
				@Override
				public void run() {
					value.incrementAndGet();
				}
			};
		}
		
		for (Thread t : ts) {
			t.start();
		}
		for (Thread t : ts) {
			t.join();
		}
		
		System.out.println(value.get());	// 15
	}

}
