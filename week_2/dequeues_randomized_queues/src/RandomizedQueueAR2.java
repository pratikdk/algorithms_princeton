import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueueAR2<Item>{
    private Item[] rqueue;
    private int[] idxQueue;
    private int tail;
    private int activeCount;
    private int deadCount;

    public RandomizedQueueAR2() {
        rqueue = (Item[]) new Object[1];
        idxQueue = new int[1];
        tail = 0;
        activeCount = 0;
        deadCount = 0;
    }

    private void resize(int capacity) {
        System.out.println("Resizing !!!!!!!!, len: " + rqueue.length + ", Cap: " + capacity);
        Item[] newRQueue = (Item[]) new Object[capacity];
        int[] newIdxQueue = new int[capacity];

        int j = 0;
        for (int i = deadCount; i < tail; i++) {
            newRQueue[j] = rqueue[i];
            newIdxQueue[j] = j;
            j++;
        }

        rqueue = newRQueue;
        idxQueue = newIdxQueue;
        // Set tail as j(which is last-most populated index)
        tail = j;
        deadCount = 0;
    }

    public void enqueue(Item item) {
        // Resize and tail fix
        if (tail == rqueue.length) {
            resize(activeCount * 2);
        }
        rqueue[tail] = item;
        idxQueue[tail] = tail;
        tail++;
        activeCount++;
    }

    public Item dequeue() {
        // Pop - Arrange - Resize

        // Random Pop
        int randomIdx = StdRandom.uniform(deadCount, tail);
        System.out.println("Random index: " + randomIdx);
        if (deadCount != randomIdx) {
            Item oldPlaceholderValue = rqueue[deadCount];
            rqueue[deadCount] = rqueue[randomIdx];
            rqueue[randomIdx] = oldPlaceholderValue;

            int oldPlaceholderIndex = idxQueue[deadCount];
            idxQueue[deadCount] = idxQueue[randomIdx];
            idxQueue[randomIdx] = oldPlaceholderIndex;
        }
        Item poppedValue = rqueue[deadCount];
        System.out.println("Popped value: " + poppedValue);
        // Adjust Counters
        deadCount++;
        activeCount--;

        // Resize
        if (activeCount > 0 && activeCount == (int)(rqueue.length*0.75)) {
            resize(activeCount * 2);
        }

        return poppedValue;
    }

    public Item sample() {
        // Random Pop
        int randomIdx = StdRandom.uniform(deadCount, tail);
        Item poppedValue = rqueue[randomIdx];
        System.out.println("poppedValue: " + poppedValue + ", Random index: " + randomIdx + ", deadcount: " + deadCount);
        return poppedValue;
    }

    public boolean isEmpty() {
        return activeCount == 0;
    }

    public int size() {
        return activeCount;
    }

    public void printRQueue() {
        System.out.println("RQueue: ");
        for (Item item: rqueue) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public void printIdxQueue() {
        System.out.println("IdxQueue: ");
        for (int item: idxQueue) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RandomizedQueueAR2<Integer> rqa = new RandomizedQueueAR2<>();

        rqa.enqueue(100);
        rqa.enqueue(200);
        rqa.enqueue(300);
        rqa.enqueue(400);
        rqa.enqueue(500);
        rqa.enqueue(600);
        rqa.enqueue(700);
        rqa.enqueue(800);

        rqa.printRQueue();
        rqa.printIdxQueue();

        System.out.println();
        System.out.println();

        rqa.dequeue(); //
        rqa.printRQueue();
        rqa.printIdxQueue();
        System.out.println();
        rqa.dequeue();//
        rqa.printRQueue();
        rqa.printIdxQueue();
        System.out.println();
        rqa.dequeue();//
        rqa.printRQueue();
        rqa.printIdxQueue();

        System.out.println();
        System.out.println();

        rqa.enqueue(1000);
        rqa.enqueue(2000);
        rqa.enqueue(3000);
        rqa.enqueue(4000);
        System.out.println();
        rqa.printRQueue();
        rqa.printIdxQueue();

        System.out.println();
        System.out.println();
        rqa.dequeue();
        rqa.printRQueue();
        rqa.printIdxQueue();
        System.out.println();
        rqa.dequeue();
        rqa.printRQueue();
        rqa.printIdxQueue();
        System.out.println();
        System.out.println();

        rqa.sample();
        rqa.sample();
        rqa.sample();
        rqa.sample();
        rqa.sample();
        rqa.sample();
    }
}
