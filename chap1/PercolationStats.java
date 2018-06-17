import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] result;
    private double rmean, rstddev;

    public PercolationStats (int n, int trails) {
        if ( n <= 0 )
            throw new IllegalArgumentException("n should be larger than 0");
        if ( trails <= 0 )
            throw new IllegalArgumentException("trails should be larger than 0");
        
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
        rmean = StdStats.mean(result);
        if (trails == 1)
            rstddev = Double.NaN;
        else rstddev = StdStats.stddev(result);
    }

    public double mean() {
        return rmean;
    }

    public double stddev() {
        return rstddev;
    }

    public double confidenceLo() {
        return rmean - 1.96 * rstddev / (Math.sqrt(result.length));
    }

    public double confidenceHi() {
        return rmean + 1.96 * rstddev / (Math.sqrt(result.length));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        PercolationStats test = new PercolationStats(n, trails);
        StdOut.println("mean                    = " + test.rmean);
        StdOut.println("stddev                  = " + test.rstddev);
        StdOut.println("95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
    }
}
