/*
 * Use half exchange instead of full exchange to reduce data movement
 *
 * Insertion sort with sentinel: eliminates the 
 * j > 0 test in the inner loop by first 
 * putting the smallest item into position.
 *
 */

import java.util.Comparator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class InsertionSortX {
    public static void sort(Comparable[] a) {
        int n = a.length;

        // put smallest elements in position to serve as sentinent
        int exchanges = 0;
        for (int i = n-1; i > 0; i--) {
            if (less(a[i], a[i-1])) {
                exch(a[i], a[i-1]);
                exchanges++;
            }
        }
        if (exchanges == 0) return;

        // insertion sort with half exchanges
        // in this way, operation on the array is half less
        for (int i = 2; i < n; i++) {
            Comparable v = a[i];
            int j = i;
            while (less(v, a[j-1])) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }

        assert isSorted(a);
    }

    private static boolean less(Comparable a, Comparable b) 
    { return a.compareTo(b) < 0; }

    private static void exch(Object[] a, int i, int j) 
    { Object t = a[i]; a[i] = a[j]; a[j] = t; }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) 
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        show(a);
        InsertionSortX.sort(a);
        show(a);
    }
}
