package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 * Class for estimating the percolation threshold.
 */
public class PercolationStats {
    private int N;
    private int T;
    //private PercolationFactory percFactory;
    private double[] percThresholds;

    /**
     * Takes three params N, T, and pf, and performs T independent computational experiments
     * on an N-by-N grid.
     */
    public PercolationStats(int N, int T, PercolationFactory pf)   {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than zero.");
        }
        if (T <= 0) {
            throw new IllegalArgumentException("T must be greater than zero.");
        }

        this.N = N;
        this.T = T;
        this.percThresholds = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }
            percThresholds[i] = ((double) percolation.numberOfOpenSites()) / (N * N);
        }

    }

    /**
     * Calculates and returns mean of percolation threshold.
     */
    public double mean() {
        return StdStats.mean(percThresholds);
    }

    /**
     * Calculates and returns standard deviation of percolation threshold.
     */
    public double stddev() {
        return StdStats.stddev(percThresholds);
    }

    /**
     * Calculates and returns low endpoint of 95% confidence interval.
     */
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    /**
     * Calculates and returns high endpoint of 95% confidence interval.
     */
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }
}
