package my.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest02 {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Semaphore sp = new Semaphore(3);
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						// 获得信号灯
						sp.acquire();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					System.out.println("线程" + Thread.currentThread().getName() + "进入，当前已有"
							+ (3 - sp.availablePermits()) + "个并发");
					
					try {
						Thread.sleep((long) (Math.random() * 10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
					sp.release();
					// 下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
					System.out.println("线程" + Thread.currentThread().getName() + "已离开，当前已有"
							+ (3 - sp.availablePermits()) + "个并发");
				}
			};
			service.execute(runnable);
		}
	}
}
