import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> pl = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Argument is null.");
        int len = points.length;
        for (int i = 0; i < len; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Element is null.");
        }
        
        Point[] aux = Arrays.copyOf(points, len);
        
        for (int i = 0; i < len; i++) {
            Arrays.sort(aux);
            Point p = points[i];
            Arrays.sort(aux, p.slopeOrder()); // Merge do not have comparator sort

            // StdOut.println(p.toString());
            // for (Point xxx : aux) { StdOut.println("->" + xxx.toString()); }
            
            int min = 0;
            while (min < aux.length && p.slopeTo(aux[min]) == Double.NEGATIVE_INFINITY) min++;
            if (min != 1) throw new IllegalArgumentException(); // check duplicate points
            int max = min;
            // StdOut.println("min and max init: " + min + "  " + max);
            
            while (min < len) {
                while (max < len && p.slopeTo(aux[max]) == p.slopeTo(aux[min])) max++;
                // StdOut.println("min and max now: " + min + "  " + max);
                if (max - min >= 3) {
                    // take record
                    Point pMin = aux[min].compareTo(p) < 0 ? aux[min] : p;
                    Point pMax = aux[max-1].compareTo(p) > 0 ? aux[max-1] : p;
                    // this statement exclude duplicates
                    if (p == pMin)
                        pl.add(new LineSegment(pMin, pMax));
                    /*
                    StdOut.println("___________________");
                    StdOut.println(i);
                    StdOut.println(min + " p: " + p.toString());
                    StdOut.println(min + " aux[min]: " + aux[min].toString());
                    StdOut.println(max-1 + " aux[max]: " + aux[max-1].toString());
                    StdOut.println("pMin: " + pMin.toString());
                    StdOut.println("pMax: " + pMax.toString());
                    StdOut.println("___________________");
                    */
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
