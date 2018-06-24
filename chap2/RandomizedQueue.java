import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] a;

    public int size() { return n; }

    //@SuppressWarnings("unchecked")
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
    }

    public boolean isEmpty() { return n == 0; }

    //@SuppressWarnings("unchecked")
    private void resize(int size) {
        assert size >= n;

        Item[] temp = (Item[]) new Object[size];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }

        a = temp;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Null item!");

        if (n == a.length) resize(n * 2);
        a[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Empty!");

        int index = StdRandom.uniform(n);
        Item temp = a[index];
        a[index] = a[n-1];
        a[n-1] = null;

        n--;

        if (n > 0 && n == a.length / 4) resize( n * 2 );
        return temp;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Empty!");
        return a[StdRandom.uniform(n)];
    }

    // method
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // class
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int index;
        private int[] indexSequence;

        // we need to randomize output of the iterator
        public RandomizedQueueIterator() {
            index = 0;
            indexSequence = new int[n];
            for (int i = 0; i < n; i++)
                indexSequence[i] = i;
            StdRandom.shuffle(indexSequence);
        }

        public boolean hasNext() { return index < n; }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Reached end!");
            return a[indexSequence[index++]];
        }
    }

    public static void main(String[] args) {
        // skip test; do test in Permutation.java
    }
}
