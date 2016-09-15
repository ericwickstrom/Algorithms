

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
		private Item[] mArray;
		private int mSize, mFirst, mLast;
		private final int mInitialSize = 10;
 		
	   public Deque(){
		   // construct an empty deque
		   mArray = (Item[]) new Object[mInitialSize];
		   mSize = 0;
		   mFirst = -1;
		   mLast = mArray.length;
	   }
	   
	   // testing purposes only
	   private Item[] getArray(){
		   return mArray;
	   }
	   
	   // called when array is full
	   private void enlargeArray(){
		   Item[] temp = (Item[]) new Object[mArray.length*2];
		   //copy the front items
		   //mFirst remains the same, since it will be X positions from
		   //the front of the array
		   for(int i = mFirst; i >= 0; i--){
			   temp[i] = mArray[i];
		   }
		   // last position will be mLast positions from the end of
		   // the new array
		   //copy the back items

		   if(mLast == mArray.length){
			   mLast = temp.length;
		   } else {
			   int oldArrayPosition = mArray.length-1;
			   int i = temp.length-1;
			   while(oldArrayPosition >= mLast){
				   temp[i--] = mArray[oldArrayPosition--];
			   }
			   mLast = temp.length - (mArray.length-mLast);
		   }
		   
		   //set mArray to temp
		   mArray = temp;
	   }
	   
	   // called when array is only filled to 1/4 of size
	   // array is then reduced by half in order to save memory
	   private void reduceArray(){
		   
		   Item[] temp = (Item[]) new Object[mArray.length/2];
		   //copy the front items
		   //mFirst remains the same, since it will be X positions from
		   //the front of the array
		   for(int i = mFirst; i >= 0; i--){
			   temp[i] = mArray[i];
		   }
		   // last position will be mLast positions from the end of
		   // the new array
		   //copy the back items
		   if(mLast == mArray.length){
			   mLast = temp.length;
		   } else {
			   int oldArrayPosition = mArray.length-1;
			   int i = temp.length-1;
			   while(oldArrayPosition >= mLast){
				   temp[i--] = mArray[oldArrayPosition--];
			   }
			   mLast = temp.length - (mArray.length-mLast);
		   }
		   
		   //set mArray to temp
		   mArray = temp;
	   }
	   
	   
	   public boolean isEmpty(){
		   // is the deque empty?
		   return mSize == 0;
	   }
	   
	   public int size(){
		   // return the number of items on the deque
		   return mSize;
	   }
	   
	   public void addFirst(Item item){
		   if(item == null) throw new NullPointerException();
		   // if array is full, resize
		   if(mArray.length == mSize) {
			   enlargeArray();
		   } 
		   mArray[++mFirst] = item;
		   mSize++;
	   }
	   
	   public void addLast(Item item){
		   if(item == null) throw new NullPointerException();
		   // add the item to the end
		   // if array is full, resize
		   if(mArray.length == mSize) {
			   enlargeArray(); 
		   }
		   mArray[--mLast] = item;
		   mSize++;
	   }
	   
	   public Item removeFirst(){
		   // remove and return the item from the front
		   if(isEmpty()){
			   throw new NoSuchElementException();
		   }
		   // if the array is only 1/4 full and greater than the initial size,
		   // resize the array so it is 1/2 of the current length
		   if (mSize == mArray.length / 4 && mArray.length > mInitialSize){
			   reduceArray();
		   }
		   Item item = null;
		   // if addFirst has not been called, first item will be in last position of
		   // the array
		   if(mFirst == -1){
			   item = mArray[mArray.length-1];
			   // delete item by shifting back items right
			   for(int i = mArray.length-1; i > mLast; i--){
				   mArray[i] = mArray[i-1];
			   }
			   // delete position by setting to null,
			   // then increment mLast position to right.
			   mArray[mLast] = null;
			   mLast++;
			   
			   
			   
			   
		   } else {
			   item = mArray[mFirst];
			   mArray[mFirst] = null;
			   mFirst--;
			   
		   }
		   mSize--;
		   return item;
	   }
	   
	   public Item removeLast(){
		   // remove and return the item from the end
		   if(isEmpty()){
			   throw new NoSuchElementException();
		   }
		   
		   // if the array is only 1/4 full and greater than the initial size,
		   // resize the array so it is 1/2 of the current length
		   if (mSize == mArray.length / 4 && mArray.length > mInitialSize){
			   reduceArray();
		   }
		   Item item = null;
		   // if mLast has not been called, last will be in position 0
		   if(mLast == mArray.length){
			   item = mArray[0];
			   // delete item by shifting front items left
			   for(int i = 0; i < mFirst; i++){
				   mArray[i] = mArray[i+1];
			   }
			   mArray[mFirst] = null;
			   mFirst--;
		   } else {
			   item = mArray[mLast];
			   mArray[mLast] = null;
			   mLast++;
			   
		   }
		   mSize--;
		   return item;
	   }
	   
	   @Override
	    public Iterator<Item> iterator() {
	        Iterator<Item> it = new Iterator<Item>() {

	            private int currentIndex = 0;
	            private Item[] array = getArrayInOrder();
	            

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
	   
	   /*
	    * Returns array of contents in sequential order
	    */
	   private Item[] getArrayInOrder(){
		   Item[] array = (Item[]) new Object[mSize];
		   int position = 0;
		   for(int i = mFirst; i >= 0; i--){
			   array[position++] = mArray[i];
		   }
		   position = mFirst;
		   for(int i = mArray.length-1; i >= mLast; i--){
			   array[++position] = mArray[i];
		   }
		   return array;
	   }
	}