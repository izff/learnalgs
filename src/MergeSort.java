import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MergeSort {

    private MergeSort() {}

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        for (int i = lo; i <= hi; i++) aux[i] = a[i];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if      (lo > mid)             a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else                           a[k] = aux[j++];
        }

        assert isSorted(a, lo, hi);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        assert lo <= hi;

        if (lo >= hi) return;

        int mid = (lo + hi) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        //create aux array here, to avoid recursion
        Comparable[] aux = new Comparable[a.length];

        int lo = 0, hi = a.length -1;
        // start the recursion
        sort(a, aux, lo, hi);
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // isSorted include both low and high
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static void main(String[] algs) {
        String[] a = StdIn.readAllStrings();
        show(a);
        sort(a);
        show(a);
    }
}
