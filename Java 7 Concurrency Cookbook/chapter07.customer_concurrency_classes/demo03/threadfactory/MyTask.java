package demo03.threadfactory;

import java.util.concurrent.TimeUnit;

public class MyTask implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
