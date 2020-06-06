public class ShellSort {

    public static void sort(Comparable[] arr) {
        int n = arr.length;

        // Declare h - sorting var
        int h = 1;
        // Generate and Re-initialize h-sort var w.r.t n using (3*h + 1)
        while (h < (n/3)) h = (3*h + 1);

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j-h]); j -= h) {
                    exch(arr, j, j-h);
                }
            }
            h = h/3;
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

    public static void main(String[] args) {
        Integer[] arrx = {3, 2, 1, 0, 19, 12, 8, 8};
        sort(arrx);
        for (Integer i: arrx) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
