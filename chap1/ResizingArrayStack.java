import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item>{
    private Item[] a;
    private int n;

    // constructor: initialize an empty stack
    public ResizingArrayStack() {
        // generic array creation is not allowed in Java
        // ask for an Object array of size 2 in the beginning
        // (Item[]) : formats the Object into Item type
        a = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;

        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;

        // another implementation
        // a = java.util.Arrays.copyOf(a, capacity);
    }

    public void push(Item item) {
        if (n == a.length) { resize(a.length * 2);}
        a[n++] = item;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow!");
        Item item = a[n-1];
        a[n-1] = null;
        n--;
        if ( n > 0 && n == a.length/4 ) { resize(a.length / 2);}

        return item;
    }

    public Item peep() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow!");
        return a[n-1];
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = n-1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) stack.push(item);
            else if (!stack.isEmpty()) StdOut.println(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left in the stack.)");
    }

    /*
    We can extract the resize part out of both push() and pop()

    public void push(Item item) {
        if (n == a.length) {
            Item[] b = (Item[]) new Object[a.length * 2];
            for (int i = 0; i < a.length; i++) b[i] = a[i];
        }
        a[n++] = item;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow!");
        if ( n <= a.length/4 ) {
            Item[] b = (Item[]) new Object[a.length/2];
            for (int i = 0; i < a.length; i++) b[i] = a[i];
        }
        Item item = a[n-1];
        a[n-1] = null;
        n--;
        return item;
    }
    */
}
