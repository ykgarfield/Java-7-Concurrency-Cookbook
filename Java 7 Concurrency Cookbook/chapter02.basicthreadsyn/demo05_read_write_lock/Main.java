package demo05_read_write_lock;

public class Main {

	public static void main(String[] args) {
		PricesInfo pricesInfo = new PricesInfo();

		// 5个读的线程
		Reader readers[] = new Reader[5];
		Thread threadsReader[] = new Thread[5];

		for (int i = 0; i < 5; i++) {
			readers[i] = new Reader(pricesInfo);
			threadsReader[i] = new Thread(readers[i]);
		}

		// 1个写的线程
		Writer writer = new Writer(pricesInfo);
		Thread threadWriter = new Thread(writer);

		for (int i = 0; i < 5; i++) {
			threadsReader[i].start();
		}
		threadWriter.start();
	}
}
