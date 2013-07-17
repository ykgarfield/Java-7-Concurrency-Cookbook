package demo07_multiconditions_lock;

public class Producer implements Runnable {
	private FileMock mock;

	private Buffer buffer;

	public Producer(FileMock mock, Buffer buffer) {
		this.mock = mock;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		buffer.setPendingLines(true);
		// 读取FileMock中所有的数据
		while (mock.hasMoreLines()) {
			String line = mock.getLine();
			buffer.insert(line);	// 存储在buffer中
		}
		
		// 一旦完成, 调用此方法告诉buffer不要再生成更多的lines
		buffer.setPendingLines(false);
	}

}
