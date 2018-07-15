import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    // private static final boolean HORIZONTAL = true;
    private static final boolean VERTICAL = false;
    private Node root;
    private Point2D nearestPoint;
    private double minDistance;

    private class Node {
        private final Point2D point;
        private final boolean status;
        private Node left;
        private Node right;
        private int size;

        public Node(Point2D p, boolean status) {
            this.point = p;
            this.status = status;
            this.size = 1;
            this.left = null;
            this.right = null;
        }
    }

    public KdTree() {
        root = null;
    }

    public boolean isEmpty() { return root == null; }

    public int size() { return size(root); }
    
    private int size(Node troot) { 
        if (troot == null) return 0; 
        else return troot.size; 
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null point");

        root = insert(root, p, VERTICAL);
    }

    private Node insert(Node troot, Point2D p, boolean status) {
        if (troot == null) return new Node(p, status);
        if (p.compareTo(troot.point) == 0) return troot;

        double diff;
        if (status == VERTICAL)
            diff = p.x() - troot.point.x();
        else
            diff = p.y() - troot.point.y();

        if (diff < 0) troot.left = insert(troot.left, p, !status);
        else troot.right = insert(troot.right, p, !status);

        troot.size = size(troot.left) + size(troot.right) + 1;

        return troot;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null point!");

        Node x = contains(root, p);
        return x != null;
    }

    private Node contains(Node troot, Point2D p) {
        if (troot == null) return null;

        if (p.compareTo(troot.point) == 0) return troot;

        double diff;
        if (troot.status == VERTICAL) diff = p.x() - troot.point.x();
        else diff = p.y() - troot.point.y();

        if (diff < 0) return contains(troot.left, p);
        else return contains(troot.right, p);

    }

    public void draw() {
        draw(root);
    }

    private void draw(Node troot) {
        if (troot == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        troot.point.draw();
        if (troot.status == VERTICAL) {
            StdDraw.setPenColor(StdDraw.BOOK_BLUE);
            StdDraw.line(troot.point.x(), 0, troot.point.x(), 1);
        } else {
            StdDraw.setPenColor(StdDraw.BOOK_RED);
            StdDraw.line(0, troot.point.y(), 1, troot.point.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("Null rect!");

        ArrayList<Point2D> points = new ArrayList<>();
        range(root, rect, points);
        return points;
    }

    private void range(Node troot, RectHV rect, ArrayList<Point2D> points) {
        if (troot == null) return;

        if (rect.contains(troot.point))
            points.add(troot.point);

        if (troot.status == VERTICAL) {
            if (rect.xmax() < troot.point.x())
                range(troot.left, rect, points);
            else if (rect.xmin() >= troot.point.x())
                range(troot.right, rect, points);
            else {
                range(troot.left, rect, points);
                range(troot.right, rect, points);
            }
        } else {
            if (rect.ymax() < troot.point.y())
                range(troot.left, rect, points);
            else if (rect.ymin() >= troot.point.y())
                range(troot.right, rect, points);
            else {
                range(troot.left, rect, points);
                range(troot.right, rect, points);
            }
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null Point!");

        nearestPoint = null;
        minDistance = Double.POSITIVE_INFINITY;
        nearest(root, p);
        return nearestPoint;
    }

    private void nearest(Node troot, Point2D p) {
        if (troot == null) return;

        double distance = p.distanceSquaredTo(troot.point);
        if (distance < minDistance) {
            minDistance = distance;
            nearestPoint = troot.point;
        }

        if (troot.status == VERTICAL) {
            if (p.x() < troot.point.x()) {
                nearest(troot.left, p);
                if (minDistance >= troot.point.x() - p.x())
                    nearest(troot.right, p);
            } else {
                nearest(troot.right, p);
                if (minDistance >= p.x() - troot.point.x())
                    nearest(troot.left, p);
            }
        } else {
            if(p.y() < troot.point.y()) {
                nearest(troot.left, p);
                if (minDistance >= troot.point.y() - p.y())
                    nearest(troot.right, p);
            } else {
                nearest(troot.right, p);
                if (minDistance >= p.y() - troot.point.y())
                    nearest(troot.left, p);
            }
        }
    }

    public static void main(String[] args) {
        // skip testing now
    }
}
