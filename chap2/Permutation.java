import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            rq.enqueue(key);
        }
        for (int i = 0; i < number; i++)
            StdOut.println(rq.dequeue());
    }
}
