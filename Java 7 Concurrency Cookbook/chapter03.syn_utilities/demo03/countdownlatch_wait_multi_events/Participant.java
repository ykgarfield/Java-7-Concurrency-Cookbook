package demo03.countdownlatch_wait_multi_events;

import java.util.concurrent.TimeUnit;

public class Participant implements Runnable {
	private Videoconference conference;

	/**
	 * 参与者的名称.只是用来做日志记录的
	 */
	private String name;

	public Participant(Videoconference conference, String name) {
		this.conference = conference;
		this.name = name;
	}

	@Override
	public void run() {
		Long duration = (long) (Math.random() * 10);
		
		try {
			// 休眠
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		conference.arrive(name);
	}

}
