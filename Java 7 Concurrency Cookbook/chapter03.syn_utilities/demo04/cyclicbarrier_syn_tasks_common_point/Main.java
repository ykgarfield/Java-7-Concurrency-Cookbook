package demo04.cyclicbarrier_syn_tasks_common_point;

import java.util.concurrent.CyclicBarrier;

public class Main {

	public static void main(String[] args) {

		/*
		 * 初始化二维数组, 10000行,每行1000个数字(1000列),查找数字5
		 */
		final int ROWS = 10000;					// 行
		final int NUMBERS = 1000;				// 列
		final int SEARCH = 5;					// 要查找的数字
		final int LINES_PARTICIPANT = 2000;		// 每个参与者要处理的行数
		MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

		// 初始化Results对象
		Results results = new Results(ROWS);

		// 创建Grouper对象
		Grouper grouper = new Grouper(results);

		// 创建CyclicBarrier对象,它有5个参与者,当它们完成,CyclicBarrier将执行grouper对象
		final int PARTICIPANTS = 5;	// 参与者数量
		CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

		// 创建、初始化和启动5个Searcher对象
		Searcher searchers[] = new Searcher[PARTICIPANTS];
		for (int i = 0; i < PARTICIPANTS; i++) {
			searchers[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock,
					results, 5, barrier);
			Thread thread = new Thread(searchers[i]);
			thread.start();
		}
		
		System.out.printf("Main: The main thread has finished.\n");
	}

}
