import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] stack;
    private int N;

    public ResizingArrayStack() {
        stack = (Item[]) new Object[1];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void resize(int newCapacity) {
        Item[] newStack = (Item[]) new Object[newCapacity];
        for (int i = 0; i < N; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

    public void push(Item item) {
        if (N == stack.length) {
            resize(2 * stack.length);
        }
        stack[N++] = item;
    }

    public Item pop() {
        Item poppedItem = stack[--N];
        if (N > 0 && N == stack.length/4) {
            resize(stack.length/2);
        }
        return poppedItem;
    }

    public Iterator<Item> iterator() {
        return new ResizingArrayStackIterator();
    }

    private class ResizingArrayStackIterator implements Iterator<Item> {
        int i = N;

        public boolean hasNext() {
            return i > 0;
        }
        public Item next() {
            return stack[--i];
        }

    }

    public static void main (String[] args) {
        System.out.println("Good!");
        ResizingArrayStack<String> ras = new ResizingArrayStack<>();

        System.out.println(ras.isEmpty());
        ras.push("Hi");
        ras.push("hello");
        ras.push("My");
        ras.push("Name");
        for (String item: ras) {
            System.out.println(item);
        }
        System.out.println();
        ras.pop();
        ras.pop();
        for (String item: ras) {
            System.out.println(item);
        }
    }
}
