import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {
    public static void shuffle(Comparable[] arr) {
        int i, r;
        int n = arr.length;

        for (i = 0; i < n; i++) {
            r = StdRandom.uniform(i+1);
            exch(arr, i, r);
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] arr, int i, int j) {
        // Swap array entry at i with j
        Comparable swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] arrx = {3, 2, 1, 0, 19, 12, 8, 8};
        shuffle(arrx);
        for (Integer i: arrx) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
