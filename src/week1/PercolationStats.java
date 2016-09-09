

import java.util.ArrayList;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;


import java.util.Random;

public class PercolationStats {
		private double mMean;
		private double mStddev;
		private double mConfidenceLo;
		private double mConfidenceHi;
		// perform trials independent experiments on an n-by-n grid
		
		public PercolationStats(int n, int trials) throws IllegalArgumentException{
			if(n <= 0 || trials <= 0) throw new IllegalArgumentException();
			double[] thresholds = new double[trials];
			double sum = 0;
			for(int trial = 0; trial < trials; trial++){
				int percolations = 0;
				Percolation p = new Percolation(n);
				Random random = new Random();
				while(!p.percolates()){
					int i = StdRandom.uniform(n) + 1;
					int j = StdRandom.uniform(n) + 1;
					if(!p.isOpen(i, j)){
						p.open(i, j);
						percolations++;
					}
					
				}
				double threshold = (double) percolations / Math.pow((double) n, 2);
				sum += threshold;
				thresholds[trial] = threshold;
			}
			// compute mean
			// mMean = sum / (double) trials;
			mMean = StdStats.mean(thresholds);
			
			// compute stddev
			/*
			sum = 0;
			for(double threshold : thresholds){
				sum += Math.pow(threshold - mMean, 2);
			}
			mStddev = Math.sqrt(sum / trials); 
			*/
			
			mStddev = StdStats.stddev(thresholds);
			// confidencelo
			mConfidenceLo = mMean - ((1.96 * mStddev) / Math.sqrt((double) trials));
			
			// confidenceHi
			mConfidenceHi = mMean - ((1.96 * mStddev) / Math.sqrt((double) trials));
		}
	   
		private int[] getPosition(int n, Random random){
			int i = 0;
			while(i == 0){
				i = random.nextInt(n+1);
			}
			int j = 0;
			while(j == 0){
				j = random.nextInt(n+1);
			}
			int[] pos = new int[2];
			pos[0] = i;
			pos[1] = j;
			return pos;
		}
		
	   public double mean(){
		   return mMean;
		   // sample mean of percolation threshold
	   }
	   
	   public double stddev(){
		   return mStddev;
		   // sample standard deviation of percolation threshold
	   }
	   
	   public double confidenceLo(){
		   return mConfidenceLo;
		   // low  endpoint of 95% confidence interval
	   }
	   
	   public double confidenceHi(){
		   return mConfidenceHi;
		   // high endpoint of 95% confidence interval
	   }

	   public static void main(String[] args){
		   // test client (described below)
	   }
	}
