package chapter01.threadmanager.demo05_sleep;

import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		FileClock clock = new FileClock();
		Thread thread = new Thread(clock);
		
		thread.start();
		
		try {
			// 等待5秒
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 中断线程
		thread.interrupt();
		
		/**
			Sat Apr 27 15:29:50 CST 2013
			Sat Apr 27 15:29:53 CST 2013
			Sat Apr 27 15:29:54 CST 2013
			Sat Apr 27 15:29:55 CST 2013
			The FileClock has been interruptedSat Apr 27 15:29:56 CST 2013
			Sat Apr 27 15:29:57 CST 2013
			Sat Apr 27 15:29:58 CST 2013
			Sat Apr 27 15:29:59 CST 2013
			Sat Apr 27 15:30:00 CST 2013
			Sat Apr 27 15:30:01 CST 2013
		*/
	}
}

