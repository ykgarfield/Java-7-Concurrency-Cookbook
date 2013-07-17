package demo05_read_write_lock;

public class Reader implements Runnable {

	private PricesInfo pricesInfo;

	public Reader(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice1());
			System.out.printf("%s: Price 2: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice2());
		}
	}

}
