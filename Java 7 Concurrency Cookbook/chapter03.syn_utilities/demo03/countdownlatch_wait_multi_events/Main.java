package demo03.countdownlatch_wait_multi_events;

public class Main {
	public static void main(String[] args) {
		// 10个参与者
		Videoconference conference = new Videoconference(10);

		// 视频-会议线程启动
		Thread threadConference = new Thread(conference);
		threadConference.start();

		// 创建10个参与者,每个使用1个线程启动
		for (int i = 0; i < 10; i++) {
			Participant p = new Participant(conference, "Participant " + i);
			Thread t = new Thread(p);
			t.start();
		}
	}
}
