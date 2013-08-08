package demo07.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {
	private List<String> buffer;

	/**
	 * 同步与消费者的交互
	 */
	private final Exchanger<List<String>> exchanger;

	public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;

		/**
		 * 10次循环交互
		 */
		for (int i = 0; i < 10; i++) {
			System.out.printf("Producer: Cycle %d\n", cycle);

			/**
			 * 每个循环中,将10个字符串加入到buffer中
			 */
			for (int j = 0; j < 10; j++) {
				String message = "Event " + ((i * 10) + j);
				System.out.printf("Producer: %s\n", message);
				buffer.add(message);
			}

			/**
			 * 与消费者进行数据交互
			 */
			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.printf("Producer: %d\n", buffer.size());
			cycle++;
		}
	}

}
