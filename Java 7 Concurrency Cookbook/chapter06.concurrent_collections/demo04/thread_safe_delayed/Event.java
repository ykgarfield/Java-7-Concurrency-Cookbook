package demo04.thread_safe_delayed;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// Delayed实现了Comparable接口
public class Event implements Delayed {
	// 开始激活的时间
	private Date startDate;

	public Event(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public int compareTo(Delayed o) {
		long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);

		if (result < 0) {
			return -1;
		} else if (result > 0) {
			return 1;
		}

		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		Date now = new Date();
		long diff = startDate.getTime() - now.getTime();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

}
