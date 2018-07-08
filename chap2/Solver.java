import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    //private int moves;
    private boolean isSolvable;
    private Node tmp;
    //private ArrayList<Board> solution;

    private class Node implements Comparable<Node> {
        private Board board;
        private Node previous;
        private int moves;
        private boolean isParity; // is this one parity of the init board?

        // for initial nodes
        public Node(Board board, boolean isParity) {
            this.board = board;
            this.previous = null;
            this.moves = 0;
            this.isParity = isParity;
        }
        
        // for later nodes
        public Node(Board board, Node previous, int moves) {
            this.board = board;
            this.previous = previous;
            this.moves = moves;
            this.isParity = previous.isParity;
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

        // use only one PQ would be faster?
        MinPQ<Node> pq = new MinPQ<>();

        pq.insert(new Node(initial, true));
        pq.insert(new Node(initial.twin(), false));

        //Node tmp;
        while (true) {
            tmp = pq.delMin();

            if (tmp.board.isGoal())
                break;
            for (Board b: tmp.board.neighbors())
                if (tmp.previous == null || !b.equals(tmp.previous.board))
                    pq.insert(new Node(b, tmp, tmp.moves + 1));
        }
        
        isSolvable = tmp.isParity && tmp.board.isGoal(); 
    }

    public boolean isSolvable() { return isSolvable; }

    public int moves() { 
        if (!isSolvable()) return -1;
        else return tmp.moves; 
    }

    public Iterable<Board> solution() { 
        if (!isSolvable()) return null;
        
        Stack<Board> solution = new Stack<Board>();
        Node node = tmp;
        while(node != null) {
            solution.push(node.board);
            node = node.previous;
        }
        
        return solution;
    }

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
