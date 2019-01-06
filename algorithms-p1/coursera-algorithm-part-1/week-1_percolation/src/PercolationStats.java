import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private final double mean;
    private final double stdDev;
    private final double confidenceLow;
    private final double confidenceHigh;

    public PercolationStats(int n, int trials) {
        double CONFIDENCE_95 = 1.96;
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException("Arguments must be greater than or equal to 1");
        }
        double[] tries = new double[trials];
        for (int i = 0; i < trials; i++) {
            // initialize samples for random sites generation and percolation
            Percolation percolation = new Percolation(n);
            int[] allSites = new int[n * n];
            int maxSiteLength = n * n;
            for (int j = 0; j < n * n; j++) {
                allSites[j] = j;
            }

            // do ops
            do {
                int indexToOpen = StdRandom.uniform(maxSiteLength);
                int itemToOpen = allSites[indexToOpen];
                allSites[indexToOpen] = allSites[maxSiteLength - 1];
                maxSiteLength -= 1;
                int row = itemToOpen / n;
                int col = itemToOpen % n;
                percolation.open(row + 1, col + 1);
            } while (!percolation.percolates());

            tries[i] = (double) percolation.numberOfOpenSites() / (n * n);
//            System.out.println(tries[i]);
        }
        mean = StdStats.mean(tries);
        stdDev = StdStats.stddev(tries);
        confidenceLow = mean - CONFIDENCE_95 * stdDev / Math.sqrt(tries.length);
        confidenceHigh = mean + CONFIDENCE_95 * stdDev / Math.sqrt(tries.length);
    }    // perform trials independent experiments on an n-by-n grid

    public double mean() {
        return mean;
    }                // sample mean of percolation threshold

    public double stddev() {
        return stdDev;
    }                       // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return confidenceLow;
    }                  // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return confidenceHigh;
    }                  // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        if (n < 1 || t < 1) {
            throw new IllegalArgumentException("Arguments must be greater than or equal to 1");
        }
        PercolationStats percolationStats = new PercolationStats(n, t);
        System.out.println("mean\t\t\t\t\t= " + percolationStats.mean());
        System.out.println("stddev\t\t\t\t\t= " + percolationStats.stddev());
        System.out.println("95% confidence interval\t= [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    } // test client (described below)
}
