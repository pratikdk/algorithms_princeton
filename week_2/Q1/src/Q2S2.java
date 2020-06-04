import java.util.Iterator;

public class Q2S2 implements Iterable<Integer> {
    private Node first;

    private class Node {
        int item;
        Node next;
    }

    public void push(int item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public int pop() {
        int  poppedValue = first.item;
        first = first.next;
        return poppedValue;
    }

    public int max() {
        return recursiveMax(first, 0);
    }

    private int recursiveMax(Node current, int currentMax) {
        if (current == null) {
            return currentMax;
        }
        if (current.item > currentMax) {
            currentMax = current.item;
        }
        return recursiveMax(current.next, currentMax);
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Iterator<Integer> iterator() {
        return new Q2S2Iterator();
    }

    private class Q2S2Iterator implements Iterator<Integer> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public Integer next() {
            int item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main (String[] args) {
        Q2S2 stackMax = new Q2S2();
        stackMax.push(2);
        stackMax.push(4);
        stackMax.push(7);
        stackMax.push(200);
        stackMax.push(199);
        stackMax.push(1000);
        stackMax.pop();
        System.out.println("Max: " + stackMax.max());
        for (int item: stackMax) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
