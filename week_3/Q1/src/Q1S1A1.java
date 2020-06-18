public class Q1S1A1 {
    private static Comparable[] aux;

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        // Offload elements from original array to auxiliary array
        int offset = 0;
        if (lo >= aux.length) {
            offset = aux.length;
        }

        for (int k = lo; k <= mid; k++) {
            aux[k-offset] = a[k];
        }


        // Setup heads
        int i = lo, j = mid+1;// i -> aux // j -> a
        for (int k = lo; k <= hi; k++) {
            // If i > mid or j > hi, means sub-array size exhausted, elements then are taken from non-exhausted sub-array
            if (i > mid) a[k] = a[j++];
            else if (j > hi) {
                a[k] = aux[i-offset];
                i++;
            }
            // or else check if element at i is less then j or else j less than i and place accordingly
            else if (less(a[j], aux[i-offset])) a[k] = a[j++];
            else {
                a[k] = aux[i-offset]; // triggers when element at i less than j and i == j
                i++;
            }
        }
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a, lo, mid);
        sort(a,mid+1, hi);
        merge(a, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        // Initialize auxiliary array, of size N/2
        int N = a.length;
        aux = new Comparable[N/2];
        sort(a, 0, N-1);
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
        sort(arr);

        for (int i: arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Is Sorted: " + isSorted(arr, 0, arr.length-1));
    }
}
