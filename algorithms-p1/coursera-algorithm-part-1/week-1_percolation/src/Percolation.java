
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final WeightedQuickUnionUF weightedQuickUnionUFIsFull;
    private boolean[] grid;
    private final int gridSideLength;
    private final int TOP_INDEX;
    private final int BOTTOM_INDEX;

    private int openSites = 0;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should not be less or equal to 0");
        }
        gridSideLength = n;
        TOP_INDEX = n * n;
        BOTTOM_INDEX = n * n + 1;
        // 1 * n^2
        grid = new boolean[n * n];
        // 2 * 2 * n^2
        weightedQuickUnionUFIsFull = new WeightedQuickUnionUF(n * n + 1);
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);

        for (int i = 0; i < n; i++) {
            // Top row connect
            weightedQuickUnionUF.union(i, n * n);
            // Bottom row connect
            weightedQuickUnionUF.union(n * (n - 1) + i, n * n + 1);
            weightedQuickUnionUFIsFull.union(i, n * n);
        }
    } // create n-by-n grid, with all sites blocked

    public void open(int row, int col) {
        checkRowCol(row, col);
        if (isOpen(row, col)) {
            return;
        }
        openSites += 1;
        grid[getIndex(row, col)] = true;
        connectOpenNeighbors(row, col);

    } // open site (row, col) if it is not open already

    private void connectOpenNeighbors(int row, int col) {
        int currIndex = getIndex(row, col);
        int destIndex;
        if (row > 1 && isOpen(row - 1, col)) {
            destIndex = getIndex(row - 1, col);
            weightedQuickUnionUF.union(destIndex, currIndex);
            weightedQuickUnionUFIsFull.union(destIndex, currIndex);
        }
        if (row < gridSideLength && isOpen(row + 1, col)) {
            destIndex = getIndex(row + 1, col);
            weightedQuickUnionUF.union(destIndex, currIndex);
            weightedQuickUnionUFIsFull.union(destIndex, currIndex);
        }

        if (col > 1 && isOpen(row, col - 1)) {
            destIndex = getIndex(row, col - 1);
            weightedQuickUnionUF.union(destIndex, currIndex);
            weightedQuickUnionUFIsFull.union(destIndex, currIndex);
        }
        if (col < gridSideLength && isOpen(row, col + 1)) {
            destIndex = getIndex(row, col + 1);
            weightedQuickUnionUF.union(destIndex, currIndex);
            weightedQuickUnionUFIsFull.union(destIndex, currIndex);
        }
    }

    public boolean isOpen(int row, int col) {
        checkRowCol(row, col);
        return grid[getIndex(row, col)];
    } // is site (row, col) open?

    public boolean isFull(int row, int col) {
        checkRowCol(row, col);
        return isOpen(row, col) && weightedQuickUnionUFIsFull.connected(getIndex(row, col), TOP_INDEX);
    } // is site (row, col) full?

    public int numberOfOpenSites() {
        return openSites;
    } // number of open sites

    public boolean percolates() {
        return numberOfOpenSites() > 0 && weightedQuickUnionUF.connected(TOP_INDEX, BOTTOM_INDEX);
    } // does the system percolate?

    private void checkRowCol(int row, int col) {
        if (row > gridSideLength || col > gridSideLength || row < 1 || col < 1) {
            throw new IllegalArgumentException("row " + row + " col " + col + " must not exceed " + gridSideLength);
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * gridSideLength + (col - 1);
    }
}
