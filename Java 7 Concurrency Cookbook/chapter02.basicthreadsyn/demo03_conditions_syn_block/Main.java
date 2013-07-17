package demo03_conditions_syn_block;

public class Main {

	public static void main(String[] args) {
		EventStorage storage = new EventStorage();

		// 生产者线程
		Producer producer = new Producer(storage);
		Thread thread1 = new Thread(producer);

		// 消费者线程
		Consumer consumer = new Consumer(storage);
		Thread thread2 = new Thread(consumer);

		thread2.start();
		thread1.start();
	}

}
