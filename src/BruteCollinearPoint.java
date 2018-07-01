import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoint {
    private ArrayList<LineSegment> ls = new ArrayList<>();

    public BruteCollinearPoint (Point[] points) {
        int len = points.length;
        Point[] aux = Arrays.copyOf(points, len);
        Arrays.sort(aux);

        for (int i = 0; i < len; i++)
            for (int j = i+1; j < len; j++)
                for (int k = j+1; k < len; k++) {
                    if (threePoints(aux[i], aux[j], aux[k])) {
                        //StdOut.println("------");
                        //StdOut.println(i + ":" + aux[i].toString());
                        //StdOut.println(j + ":" + aux[j].toString());
                        //StdOut.println(k + ":" + aux[k].toString());
                        for (int l = len - 1; l >= k+1; l--) {
                            StdOut.println("test: "+ l + ":" + aux[l].toString());
                            if (threePoints(aux[i], aux[j], aux[l])) {
                                ls.add(new LineSegment(aux[i], aux[l]));
                                break;
                            }
                        }
                        //StdOut.println("------");
                    }
                }
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
        BruteCollinearPoint collinear = new BruteCollinearPoint(points);
        StdOut.println("*********");
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
