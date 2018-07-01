import java.util.Comparator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SelectionSort {
    // this class should not be instantiated
    private SelectionSort() {}

    // the natural order sorting
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for(int j = i + 1; j < N; j++) 
                // here we should compare a[j] and a[min]
                //            rather than a[j] and a[i]
                // so as to find the smallest of the array
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }
    
    // more versatile comparator
    // when using comparator, the array should be Object[]
    public static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i; j < N; j++)
                if (less(comparator, a[j], a[min])) min = j;
            exch(a, i, min);
            assert isSorted(a, comparator, 0, i);
        }
        assert isSorted(a, comparator);
    }

    // the less function
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // the less function with comparator
    private static boolean less(Comparator comparator, Object a, Object b) {
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
    private static boolean isSorted(Object[] a, Comparator comparator, int i, int j) {
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
        SelectionSort.sort(a);
        show(a);
    }
}
