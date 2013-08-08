package demo06.control_phaser_change;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 实现了在一个测试中的一个学生
 **/
public class Student implements Runnable {
	private Phaser phaser;

	public Student(Phaser phaser) {
		this.phaser = phaser;
	}

	@Override
	public void run() {
		System.out.printf("%s: Has arrived to do the exam. %s\n", Thread.currentThread().getName(), new Date());
		phaser.arriveAndAwaitAdvance();
		
		System.out.printf("%s: Is going to do the first exercise. %s\n", Thread.currentThread().getName(), new Date());
		doExercise1();
		System.out.printf("%s: Has done the first exercise. %s\n", Thread.currentThread().getName(), new Date());
		phaser.arriveAndAwaitAdvance();
		
		System.out.printf("%s: Is going to do the second exercise. %s\n", Thread.currentThread().getName(), new Date());
		doExercise2();
		System.out.printf("%s: Has done the second exercise. %s\n", Thread.currentThread().getName(), new Date());
		phaser.arriveAndAwaitAdvance();
		
		System.out.printf("%s: Is going to do the third exercise. %s\n", Thread.currentThread().getName(), new Date());
		doExercise3();
		System.out.printf("%s: Has finished the exam. %s\n", Thread.currentThread().getName(), new Date());
		phaser.arriveAndAwaitAdvance();
	}

	/**
	 * 做一个练习等待随机的时间
	 */
	private void doExercise1() {
		try {
			Long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doExercise2() {
		try {
			Long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doExercise3() {
		try {
			Long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
