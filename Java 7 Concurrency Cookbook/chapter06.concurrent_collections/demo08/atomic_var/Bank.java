package demo08.atomic_var;

public class Bank implements Runnable {

	private Account account;

	public Bank(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			account.subtractAmount(1000);
		}
	}

}
