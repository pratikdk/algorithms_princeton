public class Q1S1 {

    private static Comparable[] aux;

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        // Offload elements from original array to auxiliary array
        int N = a.length;
        int ntoSort = hi-lo;
        if (ntoSort == N-1) {
            int nBuffer = ((N-1)/2)/2;
            offloadToAux(a, nBuffer, ntoSort-nBuffer, lo, hi);
        } else {
            if (lo < (N-1)/2) { // First half
                offloadToAux(a, ntoSort, 0, lo, hi);
            } else { // Second half
                offloadToAux(a,0, ntoSort, lo, hi);
            }
        }

        // Setup heads
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            // If i > mid or j > hi, means sub-array size exhausted, elements then are taken from non-exhausted sub-array
            if (i > mid) {
                a[k] = aux[j++];
            }
            else if (j > hi) {
                a[k] = aux[i++];
            }
            // or else check if element at i is less then j or else j less than i and place accordingly
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            }
            else {
                a[k] = aux[i++]; // triggers when element at i less than j and i == j
            }

            // Offload
            if (ntoSort == (N-1)) {
                // if first half of sub-array
                if (lo < mid) { // IF sub-array is First half of main array
                    // Is about to Exhaust ?
                    if (i == ((N-1)/2) - (((N-1)/2)/2) ) {
                        offloadToAux(a, ((N-1)/2)/2, 0, i, hi);
                    }
                } else if (lo > mid) { // Second half of main array
                    // Is about to Exhaust ?
                    if (i == (mid+((N-1)/2)) - (mid+((N-1)/2)/2) ) {
                        offloadToAux(a, ((N-1)/2)/2, 0, i, hi);
                    }
                }

            }
        }
    }

    private static void offloadToAux(Comparable[] a, int nFirstHalf, int nSecondHalf, int lo, int hi) {
        int offset = nFirstHalf + a.length/2;

        for (int k = lo; (nFirstHalf > 0) && k <= hi; k++) { // if lo < mid
            aux[k] = a[k];
            nFirstHalf--;
        }

        for (int k = lo; (nSecondHalf > 0) && k <= hi; k++) { // if lo > mid // fix indices
            aux[k-offset] = a[k];
            nSecondHalf--;
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
