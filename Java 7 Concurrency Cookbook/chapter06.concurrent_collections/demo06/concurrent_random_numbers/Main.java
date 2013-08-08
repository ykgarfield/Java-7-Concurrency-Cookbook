package demo06.concurrent_random_numbers;

public class Main {
	public static void main(String[] args) {
		Thread threads[] = new Thread[3];

		for (int i = 0; i < threads.length; i++) {
			TaskLocalRandom task = new TaskLocalRandom();
			threads[i] = new Thread(task);
			threads[i].start();
		}
	}
}
