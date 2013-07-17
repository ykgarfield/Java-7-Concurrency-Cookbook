package chapter01.threadmanager.demo08_uncheckedexception;

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		Thread thread = new Thread(task);
		// 设置未检查异常的处理
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();

//		try {
//			thread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		System.out.printf("Thread has finished\n");
	}
	
	/**
	 	An exception has been captured
		Thread: 9
		Exception: java.lang.NumberFormatException: For input string: "TTT"
		Stack Trace: 
		java.lang.NumberFormatException: For input string: "TTT"
			at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
			at java.lang.Integer.parseInt(Integer.java:492)
			at java.lang.Integer.parseInt(Integer.java:527)
			at chapter01.threadmanager.demo08_uncheckedexception.Task.run(Task.java:8)
			at java.lang.Thread.run(Thread.java:722)
		Thread status: RUNNABLE
		Thread has finished
	 */
}
