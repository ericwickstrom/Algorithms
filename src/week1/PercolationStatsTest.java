package week1;

import static org.junit.Assert.*;

import org.junit.Test;

public class PercolationStatsTest {

	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenNis0() {
		PercolationStats p = new PercolationStats(0, 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenTrialsis0() {
		PercolationStats p = new PercolationStats(1, 0);
	}
	
	/*
	 * Test Case 1
	 * n = 200 trials = 100
	 * mean = 0.5929934999999997
	 * stddev = 0.00876990421552567
	 * confidencelo = 0.5912745987737567
	 * confidencehi = 0.5947124012262428
	 */
	

	@Test
	public void testCase1Mean(){
		PercolationStats p = new PercolationStats(200, 100);
		assertEquals(0.5929934999999997, p.mean(), 0.001);
	}
	
	@Test
	public void testCase1StdDev(){
		PercolationStats p = new PercolationStats(200, 100);
		assertEquals(0.00876990421552567, p.stddev(), 0.001);
	}
	
	@Test
	public void testCase1ConfidenceLo(){
		PercolationStats p = new PercolationStats(200, 100);
		assertEquals(0.5912745987737567, p.confidenceLo(), 0.001);
	}
	
	@Test
	public void testCase1ConfidenceHi(){
		PercolationStats p = new PercolationStats(200, 100);
		assertEquals(0.5947124012262428, p.confidenceHi(), 0.005);
	}
}
