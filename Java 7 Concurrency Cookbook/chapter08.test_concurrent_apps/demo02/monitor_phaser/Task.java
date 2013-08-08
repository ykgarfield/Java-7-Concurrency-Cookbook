package demo02.monitor_phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	// 这个任务将在每个阶段休眠的时间
	private int time;

	private Phaser phaser;
	
	public Task(int time, Phaser phaser) {
		this.time = time;
		this.phaser = phaser;
	}
	
	
	/**
	 * 执行3个阶段.在每个阶段,休眠指定的时间.
	 */
	@Override
	public void run() {
		/*
		 * 达到phaser
		 */
		phaser.arrive();
		
		/*
		 * 阶段1
		 */
		System.out.printf("%s: Entering phase 1.\n",Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Finishing phase 1.\n",Thread.currentThread().getName());
		/*
		 * 阶段1结束
		 */
		phaser.arriveAndAwaitAdvance();
		
		/*
		 * 阶段2
		 */
		System.out.printf("%s: Entering phase 2.\n",Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Finishing phase 2.\n",Thread.currentThread().getName());
		/*
		 * 阶段2结束
		 */
		phaser.arriveAndAwaitAdvance();
		
		/*
		 * 阶段3
		 */
		System.out.printf("%s: Entering phase 3.\n",Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Finishing phase 3.\n",Thread.currentThread().getName());
		/*
		 * 阶段3结束
		 */
		phaser.arriveAndDeregister();
	}

}

