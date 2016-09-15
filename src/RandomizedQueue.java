

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
		private Item[] mArray;
		private final int mInitialSize = 10;
		private int mSize;
		
	   public RandomizedQueue(){
		   // construct an empty randomized queue
		   mArray = (Item[]) new Object[mInitialSize];
		   mSize = 0;
	   }
	   
	   public boolean isEmpty(){
		   // is the queue empty?
		   return mSize == 0;
		   
	   }
	   
	   public int size(){
		   // return the number of items on the queue
		   return mSize;
	   }
	   
	   public void enqueue(Item item){
		   // add the item
		   if(item == null) {
			   throw new NullPointerException();
		   }
		   if(mSize == mArray.length) enlargeArray();
		 
		   mArray[mSize] = item;
		   mSize++;
	   }
	   
	   public Item dequeue(){
		   // remove and return a random item
		   if(mSize == 0){
			   throw new NoSuchElementException();
		   }
		   if(mSize == mArray.length / 4 && mArray.length > mInitialSize) reduceArray();
		   int pos = StdRandom.uniform(mSize);
		   Item item = mArray[pos];
		   // set new open position to end of queue
		   // then set end of queue to null.
		   mArray[pos] = mArray[mSize-1];
		   // set mCurrent to null, then decrement by 1 to set new
		   // end point of queue
		   mArray[mSize-1] = null;
		   mSize--;
		   return item;
	   }
	   
	   public Item sample(){
		   // return (but do not remove) a random item
		   if(mSize == 0){
			   throw new NoSuchElementException();
		   }
		   int pos = StdRandom.uniform(mSize);
		   return mArray[pos];
	   }
	   
	   private Item[] getRandomizedArray(){
		   Item[] array = Arrays.copyOfRange(mArray, 0, mSize);
           StdRandom.shuffle(array);
           return array;
	   }
	   
	   @Override
	    public Iterator<Item> iterator() {
	        Iterator<Item> it = new Iterator<Item>() {

	            private int currentIndex = 0;
	            Item[] array = getRandomizedArray();
	            
	            @Override
	            public boolean hasNext() {
	                return currentIndex < array.length && array[currentIndex] != null;
	            }

	            @Override
	            public Item next() {
	            	if(!hasNext()) throw new NoSuchElementException();
	                return array[currentIndex++];
	            }

	            @Override
	            public void remove() {
	                throw new UnsupportedOperationException();
	            }
	        };
	        return it;
	    }
	   
	   // Called when array is full to increase array size.
	   private void enlargeArray(){
		   Item[] temp = (Item[]) new Object[mArray.length*2];
		   for(int i = 0; i < mArray.length; i++){
			   temp[i] = mArray[i];
		   }
		   mArray = temp;
	   }
	   
	   // Called when mSize is 1/4 of array.length.  A smaller array is created
	   // in order to save memory.
	   private void reduceArray(){
		   Item[] temp = (Item[]) new Object[mArray.length / 2];
		   for(int i = 0; i <= mSize; i++){
			   temp[i] = mArray[i];
		   }
		   mArray = temp;
	   }
	}
