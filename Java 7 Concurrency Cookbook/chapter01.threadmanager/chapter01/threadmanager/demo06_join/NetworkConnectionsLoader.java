package chapter01.threadmanager.demo06_join;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetworkConnectionsLoader implements Runnable {

	@Override
	public void run() {
		System.out.printf("Begining network connections loading: %s\n", new Date());
		try {
			// µÈ´ý6Ãë
//			TimeUnit.SECONDS.sleep(6);
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Network connections loading has finished: %s\n", new Date());
	}
}
