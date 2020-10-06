package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Class that models percolation system in N x N grid.
 */
public class Percolation {
    private int N;
    private boolean[][] grid;
    private int numOpen;
    private WeightedQuickUnionUF unionFindConnected;
    private WeightedQuickUnionUF unionFindNoBackWash;
    private int virtualTopSite;
    private int virtualBottomSite;

    /**
     * Create N-by-N grid, with all sites initially blocked.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be greater than zero.");
        }
        this.N = N;
        this.grid = new boolean[N][N];
        this.numOpen = 0;
        this.unionFindConnected = new WeightedQuickUnionUF(2 + N * N);
        this.unionFindNoBackWash = new WeightedQuickUnionUF(1 + N * N);
        this.virtualTopSite = N * N;
        this.virtualBottomSite = N * N + 1;
    }

    /**
     * Takes in 2D xy coordinates of row and column. Returns equivalent 1D index
     * for simplification for future use in WeightedQuickUnionUF class.
     */
    private int xyTo1D(int row, int col) {
        return col + (N * row);
    }

    /**
     * Open the site (row, col) if it is not open already.
     */
    public void open(int row, int col) {
        if ((row > (N - 1) || row < 0) || (col > (N - 1) || col < 0)) {
            throw new IndexOutOfBoundsException();
        }

        grid[row][col] = true;
        numOpen += 1;

        //Creates connections with surrounding sites if they are open.
        int site1D = xyTo1D(row, col);
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            int aboveSite1D = xyTo1D(row - 1, col);
            unionFindConnected.union(site1D, aboveSite1D);
            unionFindNoBackWash.union(site1D, aboveSite1D);
        }
        if (row + 1 < N && isOpen(row + 1, col)) {
            int belowSite1D = xyTo1D(row + 1, col);
            unionFindConnected.union(site1D, belowSite1D);
            unionFindNoBackWash.union(site1D, belowSite1D);
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            int leftSite1D = xyTo1D(row, col - 1);
            unionFindConnected.union(site1D, leftSite1D);
            unionFindNoBackWash.union(site1D, leftSite1D);
        }
        if (col + 1 < N && isOpen(row, col + 1)) {
            int rightSite1D = xyTo1D(row, col + 1);
            unionFindConnected.union(site1D, rightSite1D);
            unionFindNoBackWash.union(site1D, rightSite1D);
        }

        //every time site opened in top row, union it with top virtual site
        if (row == 0) {
            unionFindConnected.union(site1D, virtualTopSite);
            unionFindNoBackWash.union(site1D, virtualTopSite);
        }
        //every time site opened in bottom row, union it with bottom virtual site
        if (row == (N - 1)) {
            unionFindConnected.union(site1D, virtualBottomSite);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)  {
        if ((row > (N - 1) || row < 0) || (col > (N - 1) || col < 0)) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    // is the site (row, col) full?
    /**
     * Given row and column, returns boolean indicating whether site is full.
     */
    public boolean isFull(int row, int col)  {
        if ((row > (N - 1) || row < 0) || (col > (N - 1) || col < 0)) {
            throw new IndexOutOfBoundsException();
        }
        int site1D = xyTo1D(row, col);
        return unionFindConnected.connected(site1D, virtualTopSite);
    }

    /**
     * Returns integer representing number of open sites.
     */
    public int numberOfOpenSites()     {
        return numOpen;
    }

    /**
     * Returns the boolean indicating: does the system percolate?
     */
    public boolean percolates() {
        return unionFindConnected.connected(virtualBottomSite, virtualTopSite);
    }

    /**
     * Main method, unit testing with autograder.
     */
    public static void main(String[] args) {

    }
}
