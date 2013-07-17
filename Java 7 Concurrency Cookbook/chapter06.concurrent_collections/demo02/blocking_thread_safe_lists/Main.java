package demo02.blocking_thread_safe_lists;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws Exception {
		// 固定容量
		LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>(3);

		Client client = new Client(list);
		Thread thread = new Thread(client);
		thread.start();
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				// 如果列表是空的,take()方法将阻塞直到列表中有元素
				String request = list.take();
				System.out.printf("Main: Request: %s at %s. Size: %d\n", request, new Date(), list.size());
			}
			TimeUnit.MILLISECONDS.sleep(300);
		}

		System.out.printf("Main: End of the program.\n");
	}
	
	
	/**
	 	Client: 0:0 at Thu May 16 17:28:12 CST 2013.
		Client: 0:1 at Thu May 16 17:28:12 CST 2013.
		Client: 0:2 at Thu May 16 17:28:12 CST 2013.
		Client: 0:3 at Thu May 16 17:28:12 CST 2013.
		Main: Request: 0:0 at Thu May 16 17:28:12 CST 2013. Size: 0
		Main: Request: 0:1 at Thu May 16 17:28:12 CST 2013. Size: 2
		Main: Request: 0:2 at Thu May 16 17:28:12 CST 2013. Size: 1
		Client: 0:4 at Thu May 16 17:28:12 CST 2013.
		Main: Request: 0:3 at Thu May 16 17:28:13 CST 2013. Size: 1
		Main: Request: 0:4 at Thu May 16 17:28:13 CST 2013. Size: 0
		Client: 1:0 at Thu May 16 17:28:14 CST 2013.
		Client: 1:1 at Thu May 16 17:28:14 CST 2013.
		Main: Request: 1:0 at Thu May 16 17:28:14 CST 2013. Size: 0
		Client: 1:2 at Thu May 16 17:28:14 CST 2013.
		Client: 1:3 at Thu May 16 17:28:14 CST 2013.
		Main: Request: 1:1 at Thu May 16 17:28:15 CST 2013. Size: 2
		Main: Request: 1:2 at Thu May 16 17:28:15 CST 2013. Size: 2
		Main: Request: 1:3 at Thu May 16 17:28:15 CST 2013. Size: 1
		Client: 1:4 at Thu May 16 17:28:15 CST 2013.
		Main: Request: 1:4 at Thu May 16 17:28:15 CST 2013. Size: 0
		Client: 2:0 at Thu May 16 17:28:17 CST 2013.
		Client: 2:1 at Thu May 16 17:28:17 CST 2013.
		Main: Request: 2:0 at Thu May 16 17:28:17 CST 2013. Size: 0
		Main: Request: 2:1 at Thu May 16 17:28:17 CST 2013. Size: 1
		Client: 2:2 at Thu May 16 17:28:17 CST 2013.
		Client: 2:3 at Thu May 16 17:28:17 CST 2013.
		Client: 2:4 at Thu May 16 17:28:17 CST 2013.
		Main: Request: 2:2 at Thu May 16 17:28:17 CST 2013. Size: 2
		Main: Request: 2:3 at Thu May 16 17:28:17 CST 2013. Size: 1
		Main: Request: 2:4 at Thu May 16 17:28:17 CST 2013. Size: 0
		Main: End of the program.
		Client: End.
	 */
}
