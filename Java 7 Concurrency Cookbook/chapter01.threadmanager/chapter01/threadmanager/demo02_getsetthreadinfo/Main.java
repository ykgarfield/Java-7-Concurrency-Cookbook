package chapter01.threadmanager.demo02_getsetthreadinfo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

// FIXME：thread id
public class Main {

	public static void main(String[] args) {

		// 线程的优先级信息
		System.out.printf("Minimum Priority: %s\n", Thread.MIN_PRIORITY);
		System.out.printf("Normal Priority: %s\n", Thread.NORM_PRIORITY);
		System.out.printf("Maximun Priority: %s\n", Thread.MAX_PRIORITY);

		Thread threads[];
		// 内部的枚举类, 表示线程的状态
		Thread.State status[];

		// 运行10个线程, 5个设置为最大优先级别, 5个设置为最小优先 级别
		threads = new Thread[10];
		status = new Thread.State[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator(i));
			if ((i % 2) == 0) {
				// 设置优先级
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			// 设置名称
			threads[i].setName("Thread " + i);
		}

		// 等待线程结束, 同时将线程的状态信息写入到文件中
		// 这里用的JDK7中的新语法
		try (FileWriter file = new FileWriter("src/chapter01/threadmanager/demo02_getsetthreadinfo/log.txt");
				PrintWriter pw = new PrintWriter(file);) {

			// 此时线程还未运行
			for (int i = 0; i < 10; i++) {
				pw.println("Main : Status of Thread " + i + " : " + threads[i].getState());
				status[i] = threads[i].getState();	// 将线程的状态存放在status中, 此时的状态应该为NEW
			}

			// 启动线程
			for (int i = 0; i < 10; i++) {
				threads[i].start();
			}

			boolean finish = false;
			while (!finish) {
				for (int i = 0; i < 10; i++) {
					// 这里,线程运行了(有可能在这里已经终止了),所以threads[i].getState()和status[i](都是NEW的状态)肯定不同
					if (threads[i].getState() != status[i]) {
						// 将线程信息写入到文件中
						// status[i] = NEW
						writeThreadInfo(pw, threads[i], status[i]);
						// 更新状态
						status[i] = threads[i].getState();
					}
				}

				finish = true;
				for (int i = 0; i < 10; i++) {
					// 线程是否已经终止
					finish = finish && (threads[i].getState() == State.TERMINATED);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将线程的信息写入到文件中
	 */
	private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
		pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
		pw.printf("Main : Priority: %d\n", thread.getPriority());
		pw.printf("Main : Old State: %s\n", state);
		pw.printf("Main : New State: %s\n", thread.getState());
		pw.printf("Main : ************************************\n");
	}

}
