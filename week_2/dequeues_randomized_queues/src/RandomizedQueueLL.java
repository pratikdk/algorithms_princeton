public class RandomizedQueueLL<Item> {
    private Node last;
    private int size;

    public RandomizedQueueLL() {
        last = null;
        size = 0;
    }

    private class Node {
        Item item;
        Node prev;
        Node next;
        int index;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        last.next = null;
        if (oldLast != null) {
            oldLast.next = last;
            last.index = oldLast.index + 1;
        } else {
            last.index = 0;
        }
        size++;
    }

//    public Item dequeue() {
//
//    }

}
