import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {

    private ArrayList<LineSegment> pl = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        int len = points.length;

        Point[] aux = Arrays.copyOf(points, len);
        Arrays.sort(aux);

        for (int i = 0; i < len; i++) {
            if (aux[i] == null) throw new IllegalArgumentException();

            Point p = aux[i];
            Arrays.sort(aux, p.slopeOrder()); // Merge do not have comparator sort

            int min = 1;
            if (p.slopeTo(aux[min]) == Double.NEGATIVE_INFINITY)
                throw new IllegalArgumentException();
            int max = min;
            double minSlope = p.slopeTo(aux[min]);
            while (min < len) {
                while (max < aux.length && p.slopeTo(aux[max]) == minSlope) max++;
                if (max - min >= 3) {
                    // take record
                    Point pMin = aux[min].compareTo(p) < 0 ? aux[min] : p;
                    Point pMax = aux[max-1].compareTo(p) < 0 ? p : aux[max-1];
                    // this statement exclude duplicates
                    if (p == pMin)
                        pl.add(new LineSegment(pMin, pMax));
                }
                min = max; // skip scanning the points with the same slope
            }
        }
    }

    public int numberOfSegments() { return pl.size(); }

    public LineSegment[] segments() {
        return pl.toArray(new LineSegment[pl.size()]);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
