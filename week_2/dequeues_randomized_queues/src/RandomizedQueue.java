public class RandomizedQueue<Item> {
    private Item[] rqueue;
    private int tail;
    private int size;
    // Notes:
    // Use size to shrink
    // shrink before increasing the size in enqueue
    // Set popped values to null

    public RandomizedQueue() {
        rqueue = (Item[]) new Object[1];
        tail = 0;
    }

    private void resize(int capacity) {
        Item[] newQueue = (Item[]) new Object[capacity];
        int j = 0;
        for (int i = 0; i < rqueue.length; i++) {
            if (rqueue[i] != null) {
                newQueue[j++] = rqueue[i];
            }
        }
        rqueue = newQueue;
        // Set tail as j(which is last-most populated index)
        tail = j;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        // Resize and tail fix
        if (tail == rqueue.length) {
            resize(size*2);
        }
        rqueue[tail++] = item;
        size++;
    }

//    public Item dequeue() {
//
//    }


    public static void main(String[] args) {

    }
}
