package chapter01.threadmanager.demo04_controlinterruption;

import java.io.File;

public class FileSearch implements Runnable {

	private String initPath;
	private String fileName;

	public FileSearch(String initPath, String fileName) {
		this.initPath = initPath;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		File file = new File(initPath);
		if (file.isDirectory()) {
			try {
				directoryProcess(file);
			} catch (InterruptedException e) {
				System.out.printf("%s: The search has been interrupted", Thread.currentThread().getName());
				cleanResources();
			}
		}
	}

	// 清理资源,这个示例中,空的实现
	private void cleanResources() {

	}

	// 处理目录
	private void directoryProcess(File file) throws InterruptedException {
		// 得到所有的文件
		File[] list = file.listFiles();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].isDirectory()) {
					// 如果是目录,递归调用
					directoryProcess(list[i]);
				} else {
					// 文件
					fileProcess(list[i]);
				}
			}
		}

		// 检查是否中断
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

	// 处理文件
	private void fileProcess(File file) throws InterruptedException {
		// 检查文件名称
		if (file.getName().equals(fileName)) {
			System.out.printf("%s : %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
		}

		// 检查是否中断
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

}
