package demo01_synchronizedmethod.solution;

public class Main {

	public static void main(String[] args) {
		Account account = new Account();
		// 初始余额
		account.setBalance(1000);

		// 创建Company线程
		Company company = new Company(account);
		Thread companyThread = new Thread(company);

		// 创建Bank线程
		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank);

		System.out.printf("Account : Initial Balance: %f\n", account.getBalance());

		companyThread.start();
		bankThread.start();

		try {
			companyThread.join();
			bankThread.join();
			System.out.printf("Account : Final Balance: %f\n", account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 	Account : Initial Balance: 1000.000000
		Account : Final Balance: 1000.000000
	 */
}
