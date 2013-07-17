package demo02.blocking_thread_safe_lists;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable {
	private LinkedBlockingDeque<String> requestList;

	public Client(LinkedBlockingDeque<String> requestList) {
		this.requestList = requestList;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				StringBuilder request = new StringBuilder();
				request.append(i);
				request.append(":");
				request.append(j);
				try {
					// 如果列表满了,put()操作将阻塞,直到有剩余的空间.
					requestList.put(request.toString());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.printf("Client: %s at %s.\n", request, new Date());
			}
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Client: End.\n");
	}
}
