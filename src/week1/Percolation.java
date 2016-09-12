package week1;

import java.util.Arrays;

/*
 * Solution, using a boolean[][] to keep track of open positions.
 * Better solution might be to extend WeightedQuickUnionUF
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
		// private boolean[][] mGrid;
		private byte[] mGrid; // 0 closed, 1 open
		private WeightedQuickUnionUF mUF;
		// size of grid
		private int mSize;
		/*
		 * mTop & mBottom are non-real positions.
		 * All positions on the top row connect to mTop.
		 * All positions on the bottom row connect to mBottom.
		 */
		private int mTop, mBottom;
		
		// translates x / y position to a linear position
		private int getPosition(int i, int j){
			return j + (i-1)*mSize;
		}
		
		//helper method, checks if i, j are with in the grind
		private void checkBounds(int i, int j) throws IndexOutOfBoundsException{
			if(i <= 0 || j <= 0 || i > mSize || j > mSize) throw new IndexOutOfBoundsException();
		}
		
	   public Percolation(int n) throws IllegalArgumentException{
		   // create n-by-n grid, with all sites blocked
		   if(n <= 0) throw new IllegalArgumentException();
		   mSize = n;
		   mGrid = new byte[n*n+2];
		   byte b = 0;
		   Arrays.fill(mGrid, b);
		   mUF = new WeightedQuickUnionUF(n*n+2);
		   mTop = 0;
		   mBottom = n*n+1;
	   }
	   
	   public void open(int i, int j) throws IndexOutOfBoundsException{
		   // open site (row i, column j) if it is not open already
		   // open sites automatically connect to adjacent sites
		   checkBounds(i, j);
		   mGrid[getPosition(i, j)] = 1;
		   int linearPosition = getPosition(i, j);
		   
		   //top position connects to mTop, else connects to upper position if open
		   if(i == 1){
			   mUF.union(linearPosition, mTop);
		   } else if(isOpen(i-1, j)){
			   mUF.union(linearPosition, getPosition(i-1, j));
		   }
		   
		   // bottom
		   if(i == mSize){
			 mUF.union(linearPosition, mBottom);  
		   } else if(isOpen(i+1, j)){
			   mUF.union(linearPosition, getPosition(i+1, j));
		   }
		   
		   // left, only connect if position is not left most column
		   if(j != 1){
			   if(isOpen(i, j-1)){
				   mUF.union(linearPosition, getPosition(i, j-1));
			   }
		   }
		   
		   // right, only connect if position is not right most column
		   if(j != mSize){
			   if(isOpen(i, j+1)){
				   mUF.union(linearPosition, getPosition(i, j+1));
			   }
		   }
	   }
	   
	   public boolean isOpen(int i, int j) throws IndexOutOfBoundsException{
		   // is site (row i, column j) open?
		   checkBounds(i, j);
		   return mGrid[getPosition(i, j)] == 1;
	   }
	   
	   // is site (row i, column j) full?
	   // "Full" means the position has a connection to mTop
	   public boolean isFull(int i, int j) throws IndexOutOfBoundsException{
		   checkBounds(i, j);
		   return mUF.connected(mTop, getPosition(i, j));
	   }
	   
	   // does the system percolate?
	   // "Percolates" means that mTop is connected to mBottom
	   public boolean percolates(){
		   return mUF.connected(mTop, mBottom);
	   }

	   public static void main(String[] args){
		   // test client (optional)
	   }
	}
