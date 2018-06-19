import java.awt.Font;

import edu.princeton.cs.algs4.StdDraw;

public class StdDrawTest {
    private static final int DELAY = 100;
    
    public static void main (String[] args) {
       /*
           StdDraw.setPenRadius(0.05);
           StdDraw.setPenColor(StdDraw.BLUE);
           StdDraw.point(0.5, 0.5);
           StdDraw.setPenColor(StdDraw.MAGENTA);
           StdDraw.line(0.2, 0.2, 0.8, 0.2);
       */
        
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        
        int N = 100;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N * N);
        StdDraw.setPenRadius(.01);
        for (int i = 0; i < N; i++) {
            StdDraw.point(i, i);
            StdDraw.point(i, i * i);
            StdDraw.point(i, i * Math.log(i));
        }
        StdDraw.show();
        StdDraw.pause(DELAY);
    }
}