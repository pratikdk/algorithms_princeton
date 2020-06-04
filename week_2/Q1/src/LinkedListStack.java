import java.util.Iterator;

public class LinkedListStack<Item> implements Iterable<Item>{
    private Node first;

    private class Node {
        Item item;
        Node next;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public Item pop() {
        Item poppedValue = first.item;
        first = first.next;
        return poppedValue;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {
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
        LinkedListStack<String> lls = new LinkedListStack<>();
//        System.out.println(lls.first);
        lls.push("hi");
//        System.out.println(lls.first.item + ", " + lls.first.next);
        lls.push("hello");
//        System.out.println(lls.first.item + ", " + lls.first.next.item);
        lls.push("zero");
//        System.out.println(lls.first.item + ", " + lls.first.next.item);


        for (String item: lls) {
            System.out.println(item);
        }

        lls.pop();

        for (String item: lls) {
            System.out.println(item);
        }
    }
}
