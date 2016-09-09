package week1;

import static org.junit.Assert.*;

import org.junit.Test;

public class PercolationTest {

	@Test
	public void shouldBeOpen() {
		Percolation p = new Percolation(5);
		p.open(1, 2);
		assertTrue(p.isOpen(1, 2));
	}
	
	@Test public void shouldBeClosed(){
		Percolation p = new Percolation(5);
		assertFalse(p.isOpen(1, 2));
	}
	
	@Test public void shouldPercolateSize5(){
		Percolation p = new Percolation(5);
		p.open(5, 1);
		p.open(1, 1);
		p.open(2, 1);
		p.open(3, 1);
		p.open(4, 1);
		assertTrue(p.percolates());
	}
	
	@Test public void shouldBeFullSize5(){
		Percolation p = new Percolation(5);
		p.open(1, 1);
		p.open(2, 1);
		p.open(3, 1);
		assertTrue(p.isFull(3, 1));
	}
	
	@Test public void shouldNotBeFullSize5(){
		Percolation p = new Percolation(5);
		p.open(3, 4);
		assertFalse(p.isFull(3, 4));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentException(){
		Percolation p = new Percolation(0);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void shouldIndexOutOfBoundsException(){
		Percolation p = new Percolation(5);
		p.open(0, 1);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void shouldIndexOutOfBoundsException2(){
		Percolation p = new Percolation(5);
		p.open(1, 0);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void shouldIndexOutOfBoundsException3(){
		Percolation p = new Percolation(5);
		p.open(200, 1);
	}

}
