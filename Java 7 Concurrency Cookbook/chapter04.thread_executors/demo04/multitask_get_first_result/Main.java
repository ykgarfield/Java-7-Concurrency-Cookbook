package demo04.multitask_get_first_result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 运行多个任务,只取得第一个返回的结果, invokeAny()
public class Main {
	public static void main(String[] args) {
		String username = "test";
		String password = "test";

		// 两个验证对象
		UserValidator ldapValidator = new UserValidator("LDAP");
		UserValidator dbValidator = new UserValidator("DataBase");

		TaskValidator ldapTask = new TaskValidator(ldapValidator, username, password);
		TaskValidator dbTask = new TaskValidator(dbValidator, username, password);

		List<TaskValidator> taskList = new ArrayList<>();
		taskList.add(ldapTask);
		taskList.add(dbTask);

		ExecutorService executor = Executors.newCachedThreadPool();
		String result;
		try {
			// 返回第一个结束的任务的结果
			result = executor.invokeAny(taskList);
			System.out.printf("Main: Result: %s\n", result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		executor.shutdown();
		System.out.printf("Main: End of the Execution\n");
	}
	
	/**
	 	Validator DataBase: Validating a user during 1 seconds
		Validator LDAP: Validating a user during 3 seconds
		DataBase: The user has been found
		Main: Result: DataBase
		LDAP: The user has not been found
		Main: End of the Execution
	 */
}
