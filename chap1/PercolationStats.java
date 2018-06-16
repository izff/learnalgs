import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int n;
    private int trails;
    private double[] result;

    public PercolationStats (int n, int trails) {
        int i, j, count;
        result = new double[trails];
        for (int k = 0; k < trails; k++) {
            Percolation pe = new Percolation(n);
            while (!pe.percolates()) {
                i = StdRandom.uniform(n) + 1;
                j = StdRandom.uniform(n) + 1;
                pe.open(i, j);
            }
            count = pe.numberOfOpenSites();
            result[k] = (double) count / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(result);
    }

    public double stddev() {
        return StdStats.stddev(result);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / (Math.sqrt(result.length));
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / (Math.sqrt(result.length));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        PercolationStats test = new PercolationStats(n, trails);
        StdOut.println("mean                    = " + test.mean());
        StdOut.println("stddev                  = " + test.stddev());
        StdOut.println("95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
    }
}
