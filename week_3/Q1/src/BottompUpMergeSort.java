public class BottompUpMergeSort {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // Offload elements from original array to auxiliary array
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        // Setup heads
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            // If i > mid or j > hi, means sub-array size exhausted, elements then are taken from non-exhausted sub-array
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
                // or else check if element at i is less then j or else j less than i and place accordingly
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++]; // triggers when element at i less than j and i == j
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];

        for (int sz = 1; sz < N; sz = sz+sz) {
            for (int lo = 0; lo < N-sz; lo += sz+sz) {
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
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
