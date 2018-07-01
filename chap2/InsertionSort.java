import java.util.Comparator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class InsertionSort {

    // this class should not be instantiated
    private InsertionSort() {}

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
            /*
            k = i;
            for (int j = i - 1; j >= 0; j--) {
                if (less(a[j], a[k])) {
                    exch(a, k, j);
                    k = j;
                }
            }
            */
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    // comparator specifying the order
    public static void sort(Object[] a, Comparator comparator) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1], comparator); j--)
                exch(a, j, j - 1);
            assert isSorted(a, 0, i, comparator);
        }
        assert isSorted(a, comparator);
    }

    // also implement these functions because InsertionSort is often
    // used by other sorting algorithms for array of only a few elements 
    // lo : inclusive
    // hi : exclusive
    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
        assert isSorted(a, lo, hi);
    }

    // comparator specifying the order
    public static void sort(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1], comparator); j--)
                exch(a, j, j - 1);
        }
        assert isSorted(a, lo, hi, comparator);
    }

    // return index
    public static void indexSort(Comparable[] a) {
        int n = a.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(a[index[j]], a[index[j-1]]); j--)
                exch(index, j, j-1);
        }

        return index;
    }

    // the less function
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // the less function with comparator
    private static boolean less(Object a, Object b, Comparator comparator) {
        return comparator.compare(a, b) < 0;
    }

    // the exch function, should use Object class
    private static void exch(Object[] a, int i, int j) {
        Object t = a[i]; a[i] = a[j]; a[j] = t;
    }

    // to check if the array is sorted 
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // to check if the array is sorted from i to j 
    private static boolean isSorted(Comparable[] a, int i, int j) {
        for (int k = i+1; k <= j; k++)
            if (less(a[k], a[k-1])) return false;
        return true;
    }

    // to check if the array is sorted with comparator
    private static boolean isSorted(Object[] a, Comparator comparator) {
        for (int i = 1; i < a.length; i++)
            if (less(comparator, a[i], a[i-1])) return false;
        return true;
    }

    // to check if the array is sorted from i to j with comparator
    private static boolean isSorted(Object[] a, int i, int j, Comparator comparator) {
        for (int k = i+1; k <= j; k++)
            if (less(comparator, a[k], a[k-1])) return false;
        return true;
    }

    // to show the array before and after the sorting
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static void main(String[] args) {
        // use the new method to read args file
        //In in = new In(args[0]);
        //String[] stringlist = in.readAllStrings();
        String[] a = StdIn.readAllStrings();
        show(a);
        InsertionSort.sort(a);
        show(a);
    }
}
