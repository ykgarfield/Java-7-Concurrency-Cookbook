package chapter01.threadmanager.demo06_join;

import java.util.Date;

public class Main2 {

	public static void main(String[] args) {
		// 创建DataSourcesLoader线程
		DataSourcesLoader dsLoader = new DataSourcesLoader();
		Thread thread1 = new Thread(dsLoader, "DataSourceThread");
		thread1.start();

		// 创建NetworkConnectionsLoader线程
		NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
		Thread thread2 = new Thread(ncLoader, "NetworkConnectionLoader");
		thread2.start();

		try {
			thread1.join(1000);
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
	}
	
	/**
	 	Begining network connections loading: Sat Apr 27 17:13:45 CST 2013
		Begining data sources loading: Sat Apr 27 17:13:45 CST 2013
		Data sources loading has finished: Sat Apr 27 17:13:50 CST 2013
		Network connections loading has finished: Sat Apr 27 17:13:52 CST 2013
		Main: Configuration has been loaded: Sat Apr 27 17:13:52 CST 2013
	 */
	
}
