package chapter01.threadmanager.demo08_uncheckedexception;

public class Task implements Runnable {

	@Override
	public void run() {
		// 抛出异常
		// 这里的异常由ExceptionHandler处理
		Integer.parseInt("TTT");
		
		// 这里不会被执行到
		System.out.println("=====run end=====");
	}
}
