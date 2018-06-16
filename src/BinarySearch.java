import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearch
{
    public static int rank(int key, int[] a)
    {
        int lo=0;
        int hi=a.length - 1;
        while(lo <= hi)
        {
            int mid = lo + (hi-lo)/2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();
        In in2 = new In(args[1]);
        int[] newlist = in2.readAllInts();

        Arrays.sort(whitelist);

        for (int i=0; i<=newlist.length-1; i++)
        {
            int key = newlist[i];
            if (rank(key, whitelist) == -1)
            {
                StdOut.println(key);
            }
        }

    }
}
