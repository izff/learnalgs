//import edu.princeton.cs.algs4.In;
import java.util.ArrayList;

public class Board {
    private int[][] numbers;
    private final int n;

    public Board(int[][] blocks) {
        n = blocks.length;
        numbers = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                numbers[i][j] = blocks[i][j];
    }

    public int dimension() { return n; }

    public int hamming() {
        int ham = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (numbers[i][j] != 0 && numbers[i][j] != (n * i + j + 1))
                    ham++;
        return ham;
    }

    public int manhattan() {
        int man = 0;
        int goal, gi, gj;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                goal = numbers[i][j];
                if (goal != 0) {
                    goal = goal - 1;
                    gj = goal % n;
                    gi = goal / n;
                    man += i > gi ? i - gi : gi - i;
                    man += j > gj ? j - gj : gj - j;
                }
        }
        return man;
    }

    public boolean isGoal() { return hamming() == 0; }

    private void swap(int i, int j, int m, int k) {
        int tmp = numbers[i][j];
        numbers[i][j] = numbers[m][k];
        numbers[m][k] = tmp;
    }

    // return a board that is obtained by changing any pairs
    public Board twin() {
        int[][] twinNumbers = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                twinNumbers[i][j] = numbers[i][j];
        Board twin = new Board(twinNumbers);
        if (twinNumbers[0][1] != 0 && twinNumbers[0][0] != 0)
            twin.swap(0, 1, 0, 0);
        else twin.swap(1, 0, 1, 1);
        return twin;
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null || y.getClass() != this.getClass())
                return false;
        Board tmp = (Board) y;
        if (n != tmp.n) return false;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (numbers[i][j] != tmp.numbers[i][j])
                    return false;
        return true;
    }

    public Iterable<Board> neighbors() {
        int zeroi = 0, zeroj = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (numbers[i][j] == 0) {
                    zeroi = i;
                    zeroj = j;
                }
        ArrayList<Board> neighbors = new ArrayList<>();
        if (zeroi > 0) {
            Board tmp = new Board(numbers);
            tmp.swap(zeroi, zeroj, zeroi - 1, zeroj);
            neighbors.add(tmp);
        }
        if (zeroi < n - 1) {
            Board tmp = new Board(numbers);
            tmp.swap(zeroi, zeroj, zeroi + 1, zeroj);
            neighbors.add(tmp);
        }
        if (zeroj > 0) {
            Board tmp = new Board(numbers);
            tmp.swap(zeroi, zeroj, zeroi, zeroj - 1);
            neighbors.add(tmp);
        }
        if (zeroj < n - 1) {
            Board tmp = new Board(numbers);
            tmp.swap(zeroi, zeroj, zeroi, zeroj + 1);
            neighbors.add(tmp);
        }
        return neighbors;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append('\n');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                sb.append(' ').append(numbers[i][j]).append(' ');
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // testing block
        /*
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        */
    }
}
