package demo03.run_task_asynchronously;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String path;

	// 要查找的文件的扩展名
	private String extension;

	public FolderProcessor(String path, String extension) {
		this.path = path;
		this.extension = extension;
	}

	@Override
	protected List<String> compute() {
		List<String> list = new ArrayList<>(); // 保存结果, 文件的全路径
		List<FolderProcessor> tasks = new ArrayList<>(); // 保存将要处理子目录的子任务

		File file = new File(path);
		File[] content = file.listFiles();
		if (content != null) {
			for (int i = 0; i < content.length; i++) {
				if (content[i].isDirectory()) {
					// 如果是任务,执行一个新的任务
					FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
					task.fork();	// 异步执行
					tasks.add(task);
				} else {
					// 如果是文件,比较文件的扩展名和要查找的扩展名是否一致
					if (checkFile(content[i].getName())) {
						list.add(content[i].getAbsolutePath());
					}
				}
			}

			// 子任务超过50个输出信息
			if (tasks.size() > 50) {
				System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());
			}

			// 等待任务结束,处理结果
			addResultsFromTasks(list, tasks);
		}

		return list;
	}

	/**
	 * 调用join()方法等待执行结束,join()方法将返回任务的结果.
	 */
	private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
		for (FolderProcessor item : tasks) {
			list.addAll(item.join());
		}
	}

	private boolean checkFile(String name) {
		if (name.endsWith(extension)) {
			return true;
		}
		return false;
	}

}
