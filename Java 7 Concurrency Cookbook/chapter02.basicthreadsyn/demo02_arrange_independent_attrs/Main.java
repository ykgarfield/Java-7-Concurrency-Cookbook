package demo02_arrange_independent_attrs;

public class Main {

	public static void main(String[] args) {
		// 1个电影院两个卖票窗口, 两场不同的电影
		Cinema cinema = new Cinema();

		TicketOffice1 ticketOffice1 = new TicketOffice1(cinema);
		Thread thread1 = new Thread(ticketOffice1, "TicketOffice1");

		TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
		Thread thread2 = new Thread(ticketOffice2, "TicketOffice2");

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Room 1 Vacancies: %d\n", cinema.getVacanciesCinema1());
		System.out.printf("Room 2 Vacancies: %d\n", cinema.getVacanciesCinema2());

	}
	
	/**
	 	Room 1 Vacancies: 5
		Room 2 Vacancies: 6
	 */

}
