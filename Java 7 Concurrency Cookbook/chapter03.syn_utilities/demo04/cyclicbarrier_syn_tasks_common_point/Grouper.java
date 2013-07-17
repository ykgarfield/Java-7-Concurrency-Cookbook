package demo04.cyclicbarrier_syn_tasks_common_point;

/**
 * 合计存储在Resultes对象中的值,将由CyclicBarrier自动调用,当所有的Searchers结束任务
 */
public class Grouper implements Runnable {

	/**
	 * 每行出现的数字
	 */
	private Results results;

	public Grouper(Results results) {
		this.results = results;
	}

	/**
	 * 合计存储在Resultes对象中的值
	 */
	@Override
	public void run() {
		int finalResult = 0;
		System.out.printf("Grouper: Processing results...\n");
		int data[] = results.getData();
		for (int number : data) {
			finalResult += number;
		}
		System.out.printf("Grouper: Total result: %d.\n", finalResult);
	}

}
