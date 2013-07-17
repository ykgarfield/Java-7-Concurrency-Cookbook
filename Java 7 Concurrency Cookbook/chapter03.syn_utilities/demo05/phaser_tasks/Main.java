package demo05.phaser_tasks;

import java.util.concurrent.Phaser;

public class Main {
	public static void main(String[] args) {
		// 3个参与者
		Phaser phaser = new Phaser(3);

		// 创建3个FileSearch对象,每个搜索不同的目录
		FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
		FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
		FileSearch documents = new FileSearch("C:\\Documents And Settings", "log", phaser);

		Thread systemThread = new Thread(system, "System");
		systemThread.start();

		Thread appsThread = new Thread(apps, "Apps");
		appsThread.start();

		Thread documentsThread = new Thread(documents, "Documents");
		documentsThread.start();

		try {
			// 等待3个线程结束
			systemThread.join();
			appsThread.join();
			documentsThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Terminated: %s\n", phaser.isTerminated());
	}
}
