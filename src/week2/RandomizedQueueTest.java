package week2;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class RandomizedQueueTest {
	
	private void print(RandomizedQueue<Integer> rq){
		StringBuilder sb = new StringBuilder();
		Iterator<Integer> i = rq.iterator();
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
	
	// Enqueue null throws NullPointerException	
	@Test (expected = NullPointerException.class)
	public void enqueueNullItemShouldThrowNullPointerException() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		rq.enqueue(null);
	}
	
	// Dequeue on empty RandomizedQueue throws NoSuchElementException	
	@Test (expected = NoSuchElementException.class)
	public void emptyDequeueShouldThrowNoSuchElementException() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		rq.dequeue();
	}
		
	// Sample on empty RandomizedQueue throws NoSuchElementException	
	@Test (expected = NoSuchElementException.class)
	public void emptySampleShouldThrowNoSuchElementException() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		rq.sample();
	}
	
	// Inserting 1 item should return that item when dequeue'd
	@Test
	public void enqueueAndDequeueOneItem(){
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		int i = 1;
		rq.enqueue(i);
		assertEquals(Integer.valueOf(i), rq.dequeue());
	}
	
	// Inserting 1 item should return that item when sample'd
	@Test
	public void enqueueAndSampleOneItem(){
	RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		int i = 1;
		rq.enqueue(i);
		assertEquals(Integer.valueOf(i), rq.sample());
	}
	
	@Test
	public void test(){
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		for(int i = 0; i < 3; i++){
			rq.enqueue(i);
		}
		print(rq);
	}
	
}
