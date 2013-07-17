package my.concurrency_collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

public class CopyOnWriteArrayListTest {

	@Test
	public void testArrayList() {
		List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("world");
		list.add("hehe");
		
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
//			String s = iter.next();	// java.util.ConcurrentModificationException
//			list.remove(s);
			
			String s = iter.next();
			System.out.println(s);
			iter.remove();
			/*
			 	hello
				world
				hehe
			 */
		}
	}
	
	
	@Test
	public void testCopyOnWriteArrayList() {
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("hello");
		list.add("world");
		list.add("hehe");
		
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
//			String s = iter.next();
//			System.out.println(s);
//			list.remove(s);
			/*
			 	hello
				world
				hehe
			 */
			
			String s = iter.next();
			System.out.println(s);
			iter.remove();	// java.lang.UnsupportedOperationException
		}
	}
}
