import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> ls = new ArrayList<>();

    public BruteCollinearPoints (Point[] points) {
        if (points == null) throw new IllegalArgumentException("Argument is null.");
        
        int len = points.length;
        for (int i = 0; i < len; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Element is null.");
        }
        
        Point[] aux = Arrays.copyOf(points, len);
        Arrays.sort(aux);

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                checkPoints(aux[i], aux[j]);
                for (int k = j + 1; k < len; k++) {
                    if (threePoints(aux[i], aux[j], aux[k])) {
                        for (int z = k + 1; z < len; z++) {
                            // StdOut.println("test: " + l + ":" + aux[l].toString());
                            if (threePoints(aux[i], aux[j], aux[z])) {
                                ls.add(new LineSegment(aux[i], aux[z]));
                                break;
                            }
                        }
                        // StdOut.println("------");
                    }
                }
            }
        }
    }

    private static void checkPoints(Point a, Point b) {
        if (a.compareTo(b) == 0) throw new IllegalArgumentException("Duplicate elements");
    }

    private static boolean threePoints(Point a, Point b, Point c) {
        Comparator<Point> cp = a.slopeOrder();
        if (cp.compare(b, c) == 0) return true;
        return false;
    }

    public int numberOfSegments() { return ls.size(); }

    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[ls.size()];
        return ls.toArray(segments);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }        
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
            StdOut.println(p.toString());
        }
        StdDraw.show();
        
        // test
        /*
        Point[] aux = Arrays.copyOf(points, points.length);
        Arrays.sort(aux);
        for (Point p: aux) {
            StdOut.println(p.toString());
        }
        */
        
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
      
        // StdOut.println("*********");
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
