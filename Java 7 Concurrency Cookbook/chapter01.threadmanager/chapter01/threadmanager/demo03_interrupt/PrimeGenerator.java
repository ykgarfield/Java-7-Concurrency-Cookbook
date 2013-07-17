package chapter01.threadmanager.demo03_interrupt;

public class PrimeGenerator extends Thread {
	
	@Override
	public void run() {
		long number = 1L;
		
		// 循环(也称为回路：bucle)永远不会终止直到它被中断
		while(true) {
			if(isPrime(number)) {
				System.out.printf("Number % d is Prime\n", number);
			}
			
			// 如果被中断,打印信息然后结束
			if(isInterrupted()) {
				// 虽然被中断,但是线程的状态为RUNNABLE
				System.out.println("PrimeGenerator status：" + getState());
				System.out.println("The Prime Generator has been Interrupted\n");
				// 线程被中断不表示线程终止运行,如果将下面的return语句注释掉,线程还是会继续执行
				return;
			}
			
			number ++;
		}
	}
	
	
	// 是否为质数
	private boolean isPrime(long number) {
		if(number <= 2) {
			return true;
		}
		
		for(long i = 2; i < number; i++) {
			if((number % i) == 0) {
				return false;
			}
		}
		
		return true;
	}
}

