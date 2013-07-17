package chapter01.threadmanager.demo07_daemon;

import java.util.Date;

// 存储了事件信息
public class Event {
	// 事件的时间
	private Date date;

	// 事件的信息
	private String event;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}
