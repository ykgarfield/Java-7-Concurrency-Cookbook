package demo10.own_atomic_object;

import java.util.concurrent.atomic.AtomicInteger;

public class ParkingCounter extends AtomicInteger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int maxNumber;

	public ParkingCounter(int maxNumber) {
		set(0);
		this.maxNumber = maxNumber;
	}

	/**
	 * 如果有一个小于最大值的值,就增加内部的计数器. 实现了一个原子的操作.
	 */
	public boolean carIn() {
		for (;;) {
			int value = get(); // 取得当前值
			if (value == maxNumber) {
				System.out.printf("ParkingCounter: The parking is full.\n");
				return false;
			} else {
				int newValue = value + 1;
				boolean changed = compareAndSet(value, newValue);
				if (changed) {
					System.out.printf("ParkingCounter: A car has entered.\n");
					return true;
				}
			}
		}
	}

	public boolean carOut() {
		for (;;) {
			int value = get();
			if (value == 0) {
				System.out.printf("ParkingCounter: The parking is empty.\n");
				return false;
			} else {
				int newValue = value - 1;
				boolean changed = compareAndSet(value, newValue);
				if (changed) {
					System.out.printf("ParkingCounter: A car has gone out.\n");
					return true;
				}
			}
		}
	}
}
