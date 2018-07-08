import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Solverv4 {
    private int moves;

    private boolean isSolvable;
    private ArrayList<Board> solution;

    private class Node implements Comparable<Node> {
        private Board board;
        private Node previous;
        private int moves;

        public Node(Board board, Node previous, int moves) {
            this.board = board;
            this.previous = previous;
            this.moves = moves;
        }

        public int compareTo(Node that) {
            int thisNum, thatNum;
            thisNum = this.board.manhattan() + this.moves;
            thatNum = that.board.manhattan() + that.moves;
            return thisNum - thatNum;
        }
    }

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        MinPQ<Node> pq = new MinPQ<>();
        MinPQ<Node> pq2 = new MinPQ<>();

        pq.insert(new Node(initial, null, 0));
        pq2.insert(new Node(initial.twin(), null, 0));

        while (true) {
            Node tmp = pq.delMin();

            if (tmp.board.isGoal()) {
                isSolvable = true;
                moves = tmp.moves;
                solution = new ArrayList<>();
                while (tmp != null) {
                    solution.add(0, tmp.board);
                    tmp = tmp.previous;
                }
                break;
            }
            for (Board b: tmp.board.neighbors())
                if (tmp.previous == null || !b.equals(tmp.previous.board))
                    pq.insert(new Node(b, tmp, tmp.moves + 1));

            tmp = pq2.delMin();
            if (tmp.board.isGoal()) {
                isSolvable = false;
                moves = -1;
                solution = null;
                break;
            }
            for (Board b: tmp.board.neighbors())
                if (tmp.previous == null || !b.equals(tmp.previous.board))
                    pq2.insert(new Node(b, tmp, tmp.moves + 1));
        }
    }

    public boolean isSolvable() { return isSolvable; }

    public int moves() { return moves; }

    public Iterable<Board> solution() { return solution; }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
