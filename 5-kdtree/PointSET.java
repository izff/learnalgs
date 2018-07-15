import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.TreeSet;
import java.util.ArrayList;

public class PointSET {
    private TreeSet<Point2D> plist;

    public PointSET () {
        plist = new TreeSet<>();
    }

    public boolean isEmpty() { return plist.isEmpty(); }

    public int size() { return plist.size(); }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null point!");
        plist.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null point!");
        return plist.contains(p);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("Null rect!");

        ArrayList<Point2D> points = new ArrayList<>();
        for (Point2D p: plist) {
            if (rect.contains(p))
                points.add(p);
        }
        return points;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null point!");

        Point2D nearest = null;
        double minDistance = Double.POSITIVE_INFINITY;
        double temp;
        for (Point2D pm: plist) {
            temp = p.distanceTo(pm);
            if (temp < minDistance) {
                minDistance = temp;
                nearest = pm;
            }
        }
        return nearest;
    }
    
    public void draw() {
        for (Point2D p: plist)
            p.draw();
    }

    public static void main(String[] args) {
        // skip testing
    }
}
