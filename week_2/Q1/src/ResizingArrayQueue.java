import java.util.Iterator;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int head;
    private int tail;

    public ResizingArrayQueue() {
        queue = (Item[]) new Object[1];
        head = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    private void resize(int newCapacity) {
        Item[] newQueue = (Item[]) new Object[newCapacity];
        int i = 0;
        for (int j = head; j < tail; j++) {
            newQueue[i] = queue[j];
            i++;
        }
        queue = newQueue;
    }

    public void engueue(Item item) {
        int capacity = queue.length;
        if (tail == capacity) {
            if (head > 0) {
                capacity -= head;
                tail -= (head+1);
                head = 0;
            }
            resize(capacity*2);
        }
        queue[tail++] = item;
    }

    public Item dequeue() {
        Item poppedItem = queue[head++];
        if (head > 0 && head == (int)(queue.length*0.75)) {
            resize((tail-head)*2);
            tail -= (head);
            head = 0;
        }
        return poppedItem;
    }

    public Iterator<Item> iterator() {
        return new ResizingArrayQueueIterator();
    }

    private class ResizingArrayQueueIterator implements Iterator<Item> {
        int i = head;

        public boolean hasNext() {
            return i < tail;
        }
        public Item next() {
            return queue[i++];
        }
    }

    public void printQueueSize() {
        System.out.println("Queue size: " + queue.length + ", Head: " + head + ", Tail: " + tail);
    }

    public static void main (String[] args) {
        ResizingArrayQueue<String> raq = new ResizingArrayQueue<>();
        raq.engueue("Hello");
        raq.printQueueSize();
        raq.engueue("HI");
        raq.printQueueSize();
        raq.engueue("My");
        raq.printQueueSize();
        raq.engueue("zzz");
        raq.printQueueSize();
        raq.engueue("ff");
        raq.printQueueSize();
        raq.engueue("rr");
        raq.printQueueSize();
        raq.engueue("ll");
        raq.printQueueSize();
        for (String item: raq) {
            System.out.println(item);
        }
        raq.dequeue();
        raq.printQueueSize();
        raq.dequeue();
        raq.printQueueSize();
        raq.dequeue();
        raq.printQueueSize();
        raq.dequeue();
        raq.printQueueSize();
        raq.dequeue();
        raq.printQueueSize();
        raq.dequeue();
        raq.printQueueSize();
        raq.dequeue();
        raq.printQueueSize();
        for (String item: raq) {
            System.out.println(item);
        }
    }
}
