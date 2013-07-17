package chapter01.threadmanager.demo04_controlinterruption;

import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
//		FileSearch searcher = new FileSearch("C:\\", "wordpad.exe");
		FileSearch searcher = new FileSearch("E:\\", "index.jsp");
		Thread thread = new Thread(searcher);
		thread.start();
		
		// 等待1秒
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 中断线程
		thread.interrupt();
	}
	
//	Thread-0 : E:\C\index.jsp
//	Thread-0: The search has been interrupted
}

