import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Point2D;

public class KdTree {
    private Node root;
    private int size;

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;
        private boolean status;

        public Node(Point2D p, RectHV rect, boolean status) {
            this.p = p;
            this.rect = rect;
            this.status = status;
        }
    }

    public KdTree() {}

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        root = insert(root, null, p, 0);
    }

    public Node insert(Node x, Node parent, Point2D p, int status) {
        if (x == null) {
            if (size++ == 0) return new Node(p, new RectHV(0, 0, 1,1), true);

            RectHV rectOfX = parent.rect;

            if (status )
        }
    }
}

