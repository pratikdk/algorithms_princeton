import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double meanThreshold;
    private final double stddevThreshold;
    private final int trails;

    public PercolationStats(int n, int trails) {
        if (n <= 0 || trails <= 0) {
            throw new IllegalArgumentException("Input 'n' and 'trails' should be > 0.");
        }
        int i;
        this.trails = trails;
        double[] percolationThresholds = new double[trails];

        double matrixLen = n*n;

        for (i = 0; i < trails; i++) {
            Percolation p = new Percolation(n);
            p.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
            while (!p.percolates()) {
                p.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
            }
            percolationThresholds[i] = p.numberOfOpenSites()/matrixLen;
        }

        meanThreshold = StdStats.mean(percolationThresholds);
        stddevThreshold = StdStats.stddev(percolationThresholds);

    }

    // sample mean of percolation threshold
    public double mean() {
        return meanThreshold;
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddevThreshold;
    }
    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return meanThreshold - ((CONFIDENCE_95*stddevThreshold)/Math.sqrt(trails));
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return meanThreshold + ((CONFIDENCE_95*stddevThreshold)/Math.sqrt(trails));
    }
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, trails);

        System.out.println(String.format("%-40s = %f", "mean", ps.mean()));
        System.out.println(String.format("%-40s = %f", "stddev", ps.stddev()));
        System.out.println(String.format("%-40s = %s", "95% confidence interval", "["+ps.confidenceLo()+", "+ps.confidenceHi()+"]"));
    }
}
