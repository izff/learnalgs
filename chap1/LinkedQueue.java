import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedQueue<Item> implements Iterable<Item>
{
    private Node first, last;
    private int n;

    // private inner class
    private class Node
    {
        Item item;
        Node next;
    }

    public LinkedQueue()
    {
        first = null;
        last = null;
        n = 0;
        assert check();
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    // am iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() {throw new UnsupportedOperationException(); }

        public Item next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Item peek()
    {
        if (isEmpty()) throw new NoSuchElementException("Queue empty!");
        return first.item;
    }

    // check internal invariant
    private boolean check()
    {
        // check Node first
        if (n < 0) return false;
        if (n == 0)
        {
            if (first != null) return false;
            if (last != null) return false;
        }
        else if (n == 1)
        {
            if (first == null || last == null) return false;
            if (first.next != null) return false;
            if (first != last) return false;
        }
        else
        {
            if (first == null || last == null) return false;
            if (first == last) return false;
            if (first.next == null) return false;
            if (last.next != null) return false;
        }

        // check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next)
            numberOfNodes++;
        if (numberOfNodes != n) return false;

        // check internal consistency of instance variable last
        // check method: find last Node along the queue, starting from first Node
        Node lastNode = first;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        if (last != lastNode) return false;
        return true;
    }

    public Item dequeue()
    {
        if (isEmpty()) throw new NoSuchElementException("Queue empty!");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null; // to avoid loitering
        assert check();
        return item;
    }

    public void enqueue(Item item)
    {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;  // key statements
        else oldlast.next = last;     // key statements
        n++;
        assert check();
    }

    public int size()
    {
        return n;
    }

    // overload the toString method of its father object
    public String toString() 
    {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + "");
        return s.toString();
    }


    public static void main(String[] args)
    {
        LinkedQueue<String> queue = new LinkedQueue<String>();
        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on the queue.)");
    }
}
