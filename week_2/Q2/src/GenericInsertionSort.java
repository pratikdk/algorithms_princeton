public class GenericInsertionSort {

    public static void sort(Comparable[] arr) {
        int i, j;
        int n = arr.length;
        for (i = 0; i < n; i++) {
            for (j = i; j > 0;  j--) {
                if (less(arr[j], arr[j-1])) { // carry element to its appropriate position
                    exch(arr, j, j-1);
                }
                else break; // Element position is perfect, no shifting needed
            }
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

    public static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i-1])) return false;
        }
        return true;
    }

    public static void main (String[] args) {
        Integer[] arrx = {3, 2, 1, 0, 19, 12, 8, 8};
        sort(arrx);
        for (Integer i: arrx) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
}
