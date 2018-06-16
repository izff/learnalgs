import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int[][] nbyn;
    private WeightedQuickUnionUF uf;

    public Percolation(int size) {
        n = size;
        nbyn = new int[n][n];
        int size2 = n * n + 2;
        uf = new WeightedQuickUnionUF(size2);
        for (int i = 0; i < n; i++) {
            uf.union(0, i + 1);
            uf.union(size2 - 1, size2 - i - 2);
        }
    }

    public void open(int row, int col) {
        nbyn[row - 1][col - 1] = 1;
        int index = (row - 1) * n + col;
        if (validate(row, col - 1) && isOpen(row, col - 1))
            uf.union(index, index - 1);
        if (validate(row, col + 1) && isOpen(row, col + 1))
            uf.union(index, index + 1);
        if (validate(row - 1, col) && isOpen(row - 1, col))
            uf.union(index, index - n);
        if (validate(row + 1, col) && isOpen(row + 1, col))
            uf.union(index, index + n);
    }

    private boolean validate(int row, int col) {
        return (col > 0 && col <= n && row > 0 && row <= n);
    }

    public boolean isOpen(int row, int col) {
        return nbyn[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) {
        return uf.connected(0, n * (row - 1) + col);
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (isOpen(i + 1, j + 1)) count++;
        return count;
    }

    public boolean percolates() {
        return uf.connected(0, n * n + 1);
    }

    private void printpe() {
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < n; j++)
                StdOut.print(nbyn[i][j] + " ");
            StdOut.println(" ");
        }
    }

    public static void main(String[] args) {
        int n = 20;
        int i, j;
        Percolation pe = new Percolation(n);
        while (!pe.percolates()) {
            i = StdRandom.uniform(n) + 1;
            j = StdRandom.uniform(n) + 1;
            if (pe.isOpen(i, j)) continue;
            //StdOut.println(i + " " + j);
            pe.open(i, j);
            //pe.printpe();
        }
        int count;
        count = pe.numberOfOpenSites();
        StdOut.println("The critical ratio is " + (float) count / ( n * n ) + '.');
    }
}
