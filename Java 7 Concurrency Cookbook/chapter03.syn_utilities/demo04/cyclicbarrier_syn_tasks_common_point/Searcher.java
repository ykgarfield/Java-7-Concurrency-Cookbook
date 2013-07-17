package demo04.cyclicbarrier_syn_tasks_common_point;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Searcher implements Runnable {

	/**
	 * 查找的第一行
	 */
	private int firstRow;

	/**
	 * 查找的最后一行
	 */
	private int lastRow;

	/**
	 * 二维数组
	 */
	private MatrixMock mock;

	/**
	 * 存储结果
	 */
	private Results results;

	/**
	 * 要查找的数字
	 */
	private int number;

	/**
	 * CyclicBarrier控制执行
	 */
	private final CyclicBarrier barrier;

	public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number, CyclicBarrier barrier) {
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.mock = mock;
		this.results = results;
		this.number = number;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		int counter;	// 统计要查找的数字出现的次数
		System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow, lastRow);
		
		for (int i = firstRow; i < lastRow; i++) {
			int row[] = mock.getRow(i);	// 取得一行
			counter = 0;
			for (int j = 0; j < row.length; j++) {
				if (row[j] == number) {
					counter++;
				}
			}
			
			results.setData(i, counter);	// 每一行出现多少次
		}
		
		System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());
		
		try {
			//System.out.println("waiting number : " + barrier.getNumberWaiting());
			//System.out.println("await num : " + barrier.await());
			// 此方法返回当前线程的索引,从0开始.
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
