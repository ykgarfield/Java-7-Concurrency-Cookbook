package demo01.create_fork_join_pool;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 产品列表
	private List<Product> products;

	// 第一个和最后一个间隔分配到任务
	private int first;
	private int last;

	//
	private double increment;

	public Task(List<Product> products, int first, int last, double increment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		if (last - first < 10) {
			updatePrices();
		} else {
			int middle = (last + first) / 2;
			System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
			Task t1 = new Task(products, first, middle + 1, increment);
			Task t2 = new Task(products, middle + 1, last, increment);
			System.out.println("t1 : " + t1);
			System.out.println("t2 : " + t2);
			System.out.println();
			invokeAll(t1, t2);
		}
	}

	private void updatePrices() {
		System.out.println("first : " + this.first + "  last : " + last);
		System.out.println();
		for (int i = first; i < last; i++) {
			Product product = products.get(i);
			product.setPrice(product.getPrice() * (1 + increment));
		}
	}

	@Override
	public String toString() {
		return "Task [first=" + first + ", last=" + last + "]";
	}
}
