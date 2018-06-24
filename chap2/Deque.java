import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first, last;

    private class Node {
        Node previous;
        Item item;
        Node next;
    }

    public Deque() {
        first = null;
        last  = null;
        n     = 0;
    }

    public boolean isEmpty() { return n == 0; }

    public int size() { return n; }

    private void checkItem(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Null item!");
    }

    public void addFirst(Item item) {
        checkItem(item);

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst == null)
            last = first;
        else oldfirst.previous = first;
        n++;
    }

    public void addLast(Item item) {
        checkItem(item);

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.previous = oldlast;
        if (oldlast == null)
            first = last;
        else oldlast.next = last;
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Empty!");

        Item item = first.item;
        first = first.next;
        if (first == null) last = null;
        else first.previous = null;
        n--;

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Empty!");

        Item item = last.item;
        last = last.previous;
        if (last == null) first = null;
        else last.next = null;
        n--;

        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node temp = first;
        int index = 0;

        public boolean hasNext() { return index < size(); }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (hasNext()) {
                index++;
                Item item = temp.item;
                temp = temp.next;
                return item;
            }
            else throw new NoSuchElementException();
        }
    }

    public static void main(String[] args) {
        // only test first side enqueue
        Deque<String> deque = new Deque<String>();

        deque.testFirst();
    }

    private void testFirst() {
        StdOut.println("Test Push Left");

        Deque<String> deque = new Deque<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
    }
}