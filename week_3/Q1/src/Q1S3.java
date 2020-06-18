import edu.princeton.cs.algs4.StdRandom;

public class Q1S3 {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // Offload elements from original array to auxiliary array
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        // Setup heads
        int i = lo, j = mid+1;
        int choiceState;
        for (int k = lo; k <= hi; k++) {
            choiceState = StdRandom.uniform(0, 2);

            // If i > mid or j > hi, means sub-array size exhausted, elements then are taken from non-exhausted sub-array
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
                // or else check if element at i is less then j or else j less than i and place accordingly
            else if (choiceState == 1) a[k] = aux[j++];
            else a[k] = aux[i++]; // triggers when element at i less than j and i == j
        }
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void randomSort(Comparable[] a) {
        // Initialize auxiliary array, of size N
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        sort(a, aux, 0, N-1);

    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static boolean isSorted(Comparable[] arr, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++) {
            if (less(arr[i], arr[i-1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] arr = {2, 7, 4, 3, 1, 0, 5, 9, 6, 8};
        randomSort(arr);

        for (int i: arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("Is Sorted: " + isSorted(arr, 0, arr.length-1));
    }
}
