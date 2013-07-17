package demo10_separate_tasks;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// 通过CompletionService接收任务的返回结果
public class ReportProcessor implements Runnable {
	private CompletionService<String> service;

	private boolean end;

	public ReportProcessor(CompletionService<String> service) {
		this.service = service;
		end = false;
	}

	@Override
	public void run() {
		while (!end) {
			try {
				// 使用CompletionService取得返回的结果,等待20秒
				// pool()：接收和移除下一个完成的任务,需要等待指定的时间
				Future<String> result = service.poll(20, TimeUnit.SECONDS);
				if (result != null) {
					String report = result.get();
					System.out.printf("ReportReceiver: Report Recived: %s\n", report);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		System.out.printf("ReportSender: End\n");
	}

	public void setEnd(boolean end) {
		this.end = end;
	}
}
