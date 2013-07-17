package demo03.countdownlatch_wait_multi_events;

import java.util.concurrent.CountDownLatch;

/**
 * 实现了控制视频-会议
 * 
 * 使用CountDownLatch控制所有的参与者达到
 */
public class Videoconference implements Runnable {

	private final CountDownLatch controller;

	/**
	 * @param number 参与者的人数
	 */
	public Videoconference(int number) {
		controller = new CountDownLatch(number);
	}

	public void arrive(String name) {
		System.out.printf("%s has arrived.\n", name);
		// 计数器减1
		controller.countDown();
		System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
	}

	@Override
	public void run() {
		System.out.printf("VideoConference: Initialization: %d participants.\n\n", controller.getCount());

		try {
			// 等待所有的参与者, CountDownLatch内部的计数器为0立即返回
			controller.await();

			// 开始视频-会议
			System.out.printf("\r\nVideoConference: All the participants have come\n");
			System.out.printf("VideoConference: Let's start...\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
