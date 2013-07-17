package demo01_synchronizedmethod.problem;

// 模拟公司将薪水存入账户
public class Company implements Runnable {

	private Account account;

	public Company(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.addAmount(1000);
		}
	}

}
