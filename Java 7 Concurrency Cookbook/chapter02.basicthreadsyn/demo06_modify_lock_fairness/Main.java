package demo06_modify_lock_fairness;

public class Main {
	public static void main(String[] args) {
		PrintQueue printQueue = new PrintQueue();

		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread " + i);
		}

		for (int i = 0; i < 10; i++) {
			thread[i].start();
			try {
				// 每隔0.1秒启动一个线程
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
