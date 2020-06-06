/*
    Intersection of two sets. Given two arrays a[] and b[], each containing nn distinct 2D points in the plane,
    design a subquadratic algorithm to count the number of points that are contained both in array a[] and array b[].
 */
public class Q2S1 {

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

    private static int search(Comparable[] arr, Comparable key) {
        int lo = 0;
        int hi = arr.length-1;
        int mid;

        while (lo <= hi ) {
            mid = lo + (hi-lo)/2;

            if (less(key, arr[mid])) {
                hi = mid-1;
            } else if (less(arr[mid], key)) {
                lo = mid+1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    public static void findRepeats(Comparable[] a1, Comparable[] a2) {
        int n = a1.length;
        for (int i = 0; i < n; i++) {
            int returnedIndex = search(a2, a1[i]);
            if (returnedIndex >= 0) {
                System.out.println("[Index] array1: [" + i + "] == array2: [" +  returnedIndex + "]");
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


    public static void printDistance(Comparable[] arr) {
        for (Comparable pt: arr) {
            System.out.print(((Point2D) pt).getDistance() + " ");
        }
        System.out.println();
    }

    public static void printPoints(Comparable[] arr) {
        for (Comparable pt: arr) {
            System.out.print("("+((Point2D) pt).getxValue() + ", " + ((Point2D) pt).getyValue() + ") ");
        }
        System.out.println();
    }


    public static void main(String[] arr) {
        Point2D[] pts1 = new Point2D[5];
        Point2D[] pts2 = new Point2D[5];
        // Each array contains distinct 2d points
        pts1[0] = new Point2D(1, 2);
        pts1[1] = new Point2D(5, 5);
        pts1[2] = new Point2D(3, 4);
        pts1[3] = new Point2D(1, 1);
        pts1[4] = new Point2D(1, 8);

        pts2[0] = new Point2D(1, 2);
        pts2[1] = new Point2D(4, 2);
        pts2[2] = new Point2D(1, 1);
        pts2[3] = new Point2D(5, 2);
        pts2[4] = new Point2D(3, 4);

        System.out.println("Before sorting: ");
        printDistance(pts1);
        printDistance(pts2);

        sort(pts1);
        sort(pts2);

        System.out.println("After sorting: ");
        printDistance(pts1);
        printDistance(pts2);

        System.out.println("Array 1: ");
        printPoints(pts1);
        System.out.println("Array 2: ");
        printPoints(pts2);
        findRepeats(pts1, pts2);
    }
}
