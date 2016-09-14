package week2;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class DequeTest {

	private void print(Deque<Integer> deque){
		
		StringBuilder sb = new StringBuilder();
		Iterator<Integer> i = deque.iterator();
		while(i.hasNext()){
			Integer integer = i.next();
			if(integer == null){
				sb.append("x");
			} else {
				sb.append(integer);
			}
			sb.append(" ");
		}
		System.out.println(sb.toString());
	}
	
	// Initial deque should be empty
	@Test
	public void initialDequeShouldBeEmpty(){
		Deque<Integer> deque = new Deque<Integer>();
		assertTrue(deque.isEmpty());
	}
	
	// Adding an item should return that deque is not empty
	@Test
	public void dequeWithItemShouldNotBeEmpty(){
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(1);
		assertFalse(deque.isEmpty());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeFirstFromEmptyDequeShouldThrowException(){
		Deque<Integer> deque = new Deque<Integer>();
		deque.removeFirst();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeLastFromEmptyDequeShouldThrowException(){
		Deque<Integer> deque = new Deque<Integer>();
		deque.removeLast();
	}
	
	@Test
	public void removeFirstTest(){
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(5);
		assertEquals(Integer.valueOf(5), deque.removeFirst());
	}
	
	@Test
	public void removeFirstWhenLastHasBeenAddedButNotFirst(){
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(4);
		deque.addLast(3);
		assertEquals(Integer.valueOf(3), deque.removeFirst());
	}
	
	@Test
	public void removeLastWhenFirsttHasBeenAddedButNotLast(){
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(3);
		deque.addFirst(4);
		assertEquals(Integer.valueOf(4), deque.removeLast());
	}
	@Test
	public void removeLast(){
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(3);
		deque.addLast(4);
		deque.addLast(5);
		assertEquals(Integer.valueOf(5), deque.removeLast());
	}
	
	@Test public void resizeTest(){
		Integer[] testArray =  {0, 1, 2, 3, 4, 10, null, null,
				null, null, null, null, null, null, null, 5, 6, 7, 8, 9};
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(0);
		deque.addFirst(1);
		deque.addFirst(2);
		deque.addFirst(3);
		deque.addFirst(4);
		deque.addLast(9);
		deque.addLast(8);
		deque.addLast(7);
		deque.addLast(6);
		deque.addLast(5);
		deque.addFirst(10);
		assertArrayEquals(testArray, deque.getArray());
	}
	
	@Test public void resizeTestAddFirst(){
		Integer[] testArray =  {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
				11, 12, 13, 14, 15, 16, 17, 18, 19, 20, null, null, null, null, null, null
				,null, null, null, null, null, null, null, null, null, null, null, null, null };
		Deque<Integer> deque = new Deque<Integer>();
		for(int i = 0; i < 21; i++){
			deque.addFirst(i);
		}
		assertArrayEquals(testArray, deque.getArray());
	}
	
	@Test public void resizeTestAddLast(){
		Integer[] testArray =  { null, null, null, null, null, null, null, null, null, null,
				 null, null, null, null, null, null, null, null, null, 20, 19, 18, 17, 16, 15,
				 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		Deque<Integer> deque = new Deque<Integer>();
		for(int i = 0; i < 21; i++){
			deque.addLast(i);
		}
		assertArrayEquals(testArray, deque.getArray());
	}
	
	@Test
	public void reduceArrayRemoveFirstTest(){
		Integer[] testArray = {0, 1, 2, 3, null, null, null, null, null, null};
		Deque<Integer> deque = new Deque<Integer>();
		for(int i = 0; i < 20; i++){
			deque.addFirst(i);
		}
		for(int i = 0; i <= 15; i++){
			deque.removeFirst();
		}
		assertArrayEquals(testArray, deque.getArray());
	}
	
	//TODO: fix
	@Test
	public void reduceArrayRemoveLastTest(){
		Integer[] testArray = {null, null, null, null, null, null, 3, 2, 1, 0};
		Deque<Integer> deque = new Deque<Integer>();
		for(int i = 0; i < 20; i++){
			deque.addLast(i);
		}
		for(int i = 0; i <= 15; i++){
			deque.removeLast();
		}
		assertArrayEquals(testArray, deque.getArray());
	}
	
	@Test
	public void removeLastTest(){
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(5);
		assertEquals(Integer.valueOf(5), deque.removeLast());
	}
	
	@Test
	public void getArrayInOrderTest(){
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(2);
		print(deque);
		deque.addFirst(1);
		print(deque);
		deque.addLast(3);
		print(deque);
		deque.addLast(4);
		print(deque);
	}
	
}
