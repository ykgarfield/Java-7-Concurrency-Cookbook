package chapter01.threadmanager.demo02_getsetthreadinfo;

// �̵߳�״̬
public class ThreadStatus extends Thread {
	public ThreadStatus() {
		super("ThreadStatus");
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getState() + " => " + i);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ThreadStatus t = new ThreadStatus();
		System.out.println(t.getState());	// NEW
		
		t.start();							// RUNNABLE
		t.join();
		
		System.out.println("end");
		System.out.println(t.getState());	// TERMINATED
	}
	
	/**
	 	NEW
		RUNNABLE => 0
		RUNNABLE => 1
		RUNNABLE => 2
		RUNNABLE => 3
		RUNNABLE => 4
		end
		TERMINATED
	 */
}
