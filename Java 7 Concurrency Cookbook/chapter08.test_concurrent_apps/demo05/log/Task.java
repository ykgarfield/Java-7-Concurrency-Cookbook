package demo05.log;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Task implements Runnable {

	@Override
	public void run() {
		Logger logger = MyLogger.getLogger(this.getClass().getName());

		/*
		 * 写入信息表明任务开始 (FINER)
		 */
		logger.entering(Thread.currentThread().getName(), "run()");

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/*
		 * 写入信息表明任务结束 (FINER)
		 */
		logger.exiting(Thread.currentThread().getName(), "run()", Thread.currentThread());
	}

}
