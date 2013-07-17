package demo04.threadfactory_in_executor;

import java.util.Date;

public class MyThread extends Thread {

	private Date creationDate;

	private Date startDate;

	private Date finishDate;

	public MyThread(Runnable target, String name) {
		super(target, name);
		setCreationDate();
	}

	@Override
	public void run() {
		setStartDate();
		super.run();
		setFinishDate();
		System.out.printf("Thread: %s\n", toString());
	}

	public void setCreationDate() {
		creationDate = new Date();
	}

	public void setStartDate() {
		startDate = new Date();
	}

	public void setFinishDate() {
		finishDate = new Date();
	}

	public long getExecutionTime() {
		long ret;
		ret = finishDate.getTime() - startDate.getTime();
		return ret;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getName());
		buffer.append(": ");
		buffer.append(" Creation Date: ");
		buffer.append(creationDate);
		buffer.append(" : Running time: ");
		buffer.append(getExecutionTime());
		buffer.append(" Milliseconds.");
		return buffer.toString();
	}
}
