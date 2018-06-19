import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedStack<Item>
{
    private Node first;
    private int n;

    // private inner class
    private class Node
    {
        Item item;
        Node next;
    }

    public LinkedStack()
    {
        first = null;
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

    // am oterator, doesn't implement remove() since it's optional
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
        if (isEmpty()) throw new NoSuchElementException("Stack empty!");
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
        }
        else if (n == 1)
        {
            if (first == null) return false;
            if (first.next != null) return false;
        }
        else
        {
            if (first == null) return false;
            if (first.next == null) return false;
        }

        // check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x != null; x = x.next) numberOfNodes++;
        if (numberOfNodes != n) return false;

        return true;
    }

    public Item pop()
    {
        if (isEmpty()) throw new NoSuchElementException("Stack empty!");
        Item item = first.item;
        first = first.next;
        n--;
        assert check();
        return item;
    }

    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
        assert check();
    }

    public int size()
    {
        return n;
    }

    public static void main(String[] args)
    {
        LinkedStack<String> stack = new LinkedStack<String>();
        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on the stack.)");
    }
}
