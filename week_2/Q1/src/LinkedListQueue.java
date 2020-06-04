import java.util.Iterator;

public class LinkedListQueue<Item> implements Iterable<Item> {
    private Node first, last;

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last; // For first push
        } else {
            oldLast.next = last;
        }
    }

    public Item dequeue() {
        Item poppedElement = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return poppedElement;
    }

    private class Node {
        Item item;
        Node next;
    }

    public Iterator<Item> iterator() {
        return new LinkedListQueueIterator();
    }

    private class LinkedListQueueIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main (String[] args) {
        LinkedListQueue<String> llq = new LinkedListQueue<String>();

        llq.enqueue("Hello");
        llq.enqueue("My");
        llq.enqueue("Name");
        llq.enqueue("is");
        llq.enqueue("mr.x");
        for (String item: llq) {
            System.out.println(item);
        }
        llq.dequeue();
        llq.dequeue();
        llq.dequeue();
        llq.dequeue();
        for (String item: llq) {
            System.out.println(item);
        }
    }

}
