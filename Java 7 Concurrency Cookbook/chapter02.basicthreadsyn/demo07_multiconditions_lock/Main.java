package demo07_multiconditions_lock;

public class Main {
	public static void main(String[] args) {
		FileMock mock = new FileMock(101, 10);

		Buffer buffer = new Buffer(20);

		// 1个生产者
		Producer producer = new Producer(mock, buffer);
		Thread threadProducer = new Thread(producer, "Producer");

		// 3个消费者
		Consumer consumers[] = new Consumer[3];
		Thread threadConsumers[] = new Thread[3];

		for (int i = 0; i < 3; i++) {
			consumers[i] = new Consumer(buffer);
			threadConsumers[i] = new Thread(consumers[i], "Consumer " + i);
		}

		threadProducer.start();
		for (int i = 0; i < 3; i++) {
			threadConsumers[i].start();
		}
	}
}
