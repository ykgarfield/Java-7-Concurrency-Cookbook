package demo02.join_results_task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class LineTask extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] line;

	private int start, end;

	private String word;

	public LineTask(String[] line, int start, int end, String word) {
		this.line = line;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	@Override
	protected Integer compute() {
		Integer result = null;

		if (end - start < 10) {
			// 小于10个单词,直接计算结果
			result = count(line, start, end, word);
		} else {
			// 分隔任务
			int mid = (start + end) / 2;
			LineTask task1 = new LineTask(line, start, mid, word);
			LineTask task2 = new LineTask(line, mid, end, word);

			invokeAll(task1, task2);

			try {
				result = groupResults(task1.get(), task2.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 计算结果
	private Integer count(String[] line, int start, int end, String word) {
		int counter = 0;
		for (int i = start; i < end; i++) {
			if (line[i].equals(word)) {
				counter++;
			}
		}

		try {
			// 减缓程序的执行,休眠一会儿
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return counter;
	}

	private Integer groupResults(Integer number1, Integer number2) {
		Integer result;

		result = number1 + number2;
		return result;
	}

}
