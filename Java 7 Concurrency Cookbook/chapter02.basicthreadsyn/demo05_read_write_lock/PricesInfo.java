package demo05_read_write_lock;

import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PricesInfo {

	// 两种价格
	private double price1;
	private double price2;

	// 控制价格的锁
	private ReadWriteLock lock;

	public PricesInfo() {
		price1 = 1.0;
		price2 = 2.0;
		
		lock = new ReentrantReadWriteLock();
	}
	
	// 获得第一个价格
	public double getPrice1() {
		// 获取read锁
		lock.readLock().lock();
		
		// 自己加上一些调试信息
		System.out.println("price1 获得read锁   =====>");	
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double value = price1;
		System.out.println("price1 获得read锁   <=====");
		
		lock.readLock().unlock();
		
		return value;
	}
	
	// 获得第一个价格
	public double getPrice2() {
		// 获取read锁
		lock.readLock().lock();
		
		System.out.println("price2 获得read锁   =====>");	// 自己加的打印语句
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double value = price2;
		System.out.println("price2 获得read锁   <=====");
		
		lock.readLock().unlock();
		
		return value;
	}
	
	// 设置价格
	public void setPrices(double price1, double price2) {
		// 获得write锁
		lock.writeLock().lock();
		System.out.println("获得write锁 at " + new Date());
		try {
			Thread.sleep(2 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.price1 = price1;
		this.price2 = price2;
		
		System.out.println("释放锁           at " + new Date());
		lock.writeLock().unlock();
	}
}
