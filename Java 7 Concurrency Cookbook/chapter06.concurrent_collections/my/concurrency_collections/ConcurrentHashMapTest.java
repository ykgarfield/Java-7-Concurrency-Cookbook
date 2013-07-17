package my.concurrency_collections;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class ConcurrentHashMapTest {

	/**
	 * putIfAbsent()一旦设置了某个不存在的key,这个key就不会改变
	 * 相当于如下的操作：
	 * if(!map.containsKey(key)) {
	 * 	   return map.put(key, value);
	 * } else {
	 *     return map.get(key);
	 * }
	 */
	@Test
	public void testPutIfAbsent() {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

		// 不存在name的key, 返回null
		String r = map.putIfAbsent("name", "Tom");
		System.out.println(r);	// null

		// 改变name的值,没有改变
		r = map.putIfAbsent("name", "John");
		System.out.println(r);	// Tom
		
		r = map.putIfAbsent("name", "Cat");
		System.out.println(r);	// Tom
		
		System.out.println(map);	// {name=Tom}
	}
}
