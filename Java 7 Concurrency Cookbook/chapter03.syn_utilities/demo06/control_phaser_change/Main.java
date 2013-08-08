package demo06.control_phaser_change;

public class Main {
	public static void main(String[] args) {
		MyPhaser phaser = new MyPhaser();

		// 创建5个sutdents,然后在phaser中注册
		Student[] students = new Student[5];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(phaser);
			phaser.register();
		}

		Thread threads[] = new Thread[students.length];
		for (int i = 0; i < students.length; i++) {
			threads[i] = new Thread(students[i], "Student " + i);
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 检查Phaser是否处于终止状态
		System.out.printf("Main: The phaser has finished: %s.\n", phaser.isTerminated());
	}
}
