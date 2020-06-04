import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Passed null argument.");
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldFirst;
        if (last == null) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Passed null argument.");
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        last.next = null;
        if (first == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null || isEmpty()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) {

        Deque<String> dq = new Deque<>();
        System.out.println("Is empty: " + dq.isEmpty());
        dq.addFirst("1");
        dq.addLast("2");
        dq.addLast("3");
        dq.addLast("4");
        dq.addFirst("100");
        dq.addLast("999");

        for (String item: dq) {
            System.out.print(item + " ");
        }
        System.out.println();

        System.out.println("Deque size: " + dq.size);

        dq.removeFirst();
        dq.removeLast();
        dq.removeFirst();
        dq.removeLast();
        for (String item: dq) {
            System.out.print(item + " ");
        }
        System.out.println();

        System.out.println("Deque size: " + dq.size);

        dq.removeLast();
        dq.removeLast();
        System.out.println("Is empty: " + dq.isEmpty());
        System.out.println("Is empty: " + dq.isEmpty());
    }
}
