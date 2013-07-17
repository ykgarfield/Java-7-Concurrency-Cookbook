package demo01.create_fork_join_pool;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Product> products;

	private int first;
	private int last;

	private double increment;

	@Override
	protected void compute() {

	}

}
