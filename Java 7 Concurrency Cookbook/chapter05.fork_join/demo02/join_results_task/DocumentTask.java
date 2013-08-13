package demo02.join_results_task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class DocumentTask extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 要处理的文档
	private String[][] document;

	// 需要处理的文档的行范围
	private int start, end;

	// 要查找的关键字
	private String word;

	public DocumentTask(String[][] document, int start, int end, String word) {
		this.document = document;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	@Override
	protected Integer compute() {
		Integer result = null;
		if (end - start < 10) {
			// 小于10行, 直接处理
			result = processLines(document, start, end, word);
		} else {
			// 分隔任务
			int mid = (start + end) / 2;
			DocumentTask task1 = new DocumentTask(document, start, mid, word);
			DocumentTask task2 = new DocumentTask(document, mid, end, word);

			// 处理任务
			invokeAll(task1, task2);

			try {
				// 计算结果
				result = groupResults(task1.get(), task2.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 处理行 => 行任务
	private Integer processLines(String[][] document, int start, int end, String word) {
		List<LineTask> tasks = new ArrayList<>();

		for (int i = start; i < end; i++) {
			// 行任务
			LineTask task = new LineTask(document[i], 0, document[i].length, word);
			tasks.add(task);
		}

		invokeAll(tasks);

		int result = 0;
		// 处理返回结果
		for (int i = 0; i < tasks.size(); i++) {
			LineTask task = tasks.get(i);

			try {
				result = result + task.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		return new Integer(result);
	}

	// 计算结果
	private Integer groupResults(Integer number1, Integer number2) {
		Integer result;
		result = number1 + number2;

		return result;
	}

}
