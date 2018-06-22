import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int[][] nbyn;
    private WeightedQuickUnionUF uf;
    private boolean[] todown;
    private boolean percolateflag = false;

    public Percolation(int size) {
        if (size <= 0) 
            throw new IllegalArgumentException("size should be larger than 0");
        
        n = size;
        nbyn = new int[n][n];
        todown = new boolean[n*n + 1];
        int size2 = n * n + 2;
        uf = new WeightedQuickUnionUF(size2);
    }

    public void open(int row, int col) {
        boolean top = false;
        boolean down = false;
        if (!validate(row, col))
            throw new IllegalArgumentException("Index out of range.");
            
        nbyn[row - 1][col - 1] = 1;
        int index = (row - 1) * n + col;
        
        if (row == 1) uf.union(0, index);
        
        // connect with the neighbour if possible
        // also check if the root node of neighbor is connected to the down node
        // if so, make the ij node connected to the down node
        // Note: the power of uf.find()
        if (validate(row, col - 1) && isOpen(row, col - 1)) {
            if (todown[uf.find(index - 1)] || todown[uf.find(index)]) 
                down = true; 
            uf.union(index, index - 1);
        }
        if (validate(row, col + 1) && isOpen(row, col + 1)) {
            if (todown[uf.find(index + 1)] || todown[uf.find(index)]) 
                down = true; 
            uf.union(index, index + 1);
        }
        if (validate(row - 1, col) && isOpen(row - 1, col)) {
            if (todown[uf.find(index - n)] || todown[uf.find(index)]) 
                down = true; 
            uf.union(index, index - n);
        }
        if (validate(row + 1, col) && isOpen(row + 1, col)) {
            if (todown[uf.find(index + n)] || todown[uf.find(index)]) 
                down = true; 
            uf.union(index, index + n);
        }

        /*
        if (validate(row, col - 1) && isOpen(row, col - 1) && !uf.connected(index, index - 1))
            uf.union(index, index - 1);
        if (validate(row, col + 1) && isOpen(row, col + 1) && !uf.connected(index, index + 1))
            uf.union(index, index + 1);
        if (validate(row - 1, col) && isOpen(row - 1, col) && !uf.connected(index, index - n))
            uf.union(index, index - n);
        if (validate(row + 1, col) && isOpen(row + 1, col) && !uf.connected(index, index + n))
            uf.union(index, index + n);
        */

        // if ij is in the last row, it is certainly connected to 
        // the virtual down node
        if (row == n)
            down = true;

        
        top = uf.connected(0, index);
        todown[uf.find(index)] = down;

        //StdOut.println("Top is " + top + " and down is " + down + ".");
        
        if (top && down)
            percolateflag = true;
    }

    private boolean validate(int row, int col) {
        return (col > 0 && col <= n && row > 0 && row <= n);
    }

    public boolean isOpen(int row, int col) {
        if (!validate(row, col))
            throw new IllegalArgumentException("Index out of range.");

        return nbyn[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) {
        if (!validate(row, col))
            throw new IllegalArgumentException("Index out of range.");

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
        return percolateflag;
    }

    /*
    private void printpe() {
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < n; j++)
                StdOut.print(nbyn[i][j] + " ");
            StdOut.println(" ");
        }
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < n; j++)
                StdOut.print(todown[i*n + j + 1] + " ");
            StdOut.println(" ");
        }
        StdOut.println("------------------------");
    }
    */
    
    public static void main(String[] args) {
        int n = 4;
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
