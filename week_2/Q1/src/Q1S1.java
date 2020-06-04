public class Q1S1<Item> {
    private LinkedListStack<Item> stack1;
    private LinkedListStack<Item> stack2;

    public Q1S1() {
        stack1 = new LinkedListStack<>();
        stack2 = new LinkedListStack<>();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public void enqueue(Item item) {
        stack1.push(item);
    }

    public Item dequeue() {
        if (stack2.isEmpty() && !stack1.isEmpty()) {
            // Offload all elements from stack1 to stack2
            reversePop();
        }
        return stack2.pop();
    }

    private void reversePop() {
        if (stack1.isEmpty()) {
            return;
        }
        Item poppedItem = stack1.pop();
        reversePop();
        stack2.push(poppedItem);
    }

    public void printStack1() {
        System.out.print("Stack1: ");
        for (Item item: stack1) {
            System.out.print("[" + item + "] ");
        }
        System.out.println();
    }

    public void printStack2() {
        System.out.print("Stack2: ");
        for (Item item: stack2) {
            System.out.print("[" + item + "] ");
        }
        System.out.println();
    }

    public static void main (String[] args) {
        Q1S1<String> stackedQueue = new Q1S1<>();
        stackedQueue.enqueue("Hi");
        stackedQueue.enqueue("Hello");
        stackedQueue.enqueue("Howdy");
        stackedQueue.enqueue("Wassup");
        stackedQueue.printStack1();

        System.out.println("Popped: " + stackedQueue.dequeue());
        System.out.println("Popped: " + stackedQueue.dequeue());
        stackedQueue.printStack2();
        stackedQueue.printStack1();
    }
}