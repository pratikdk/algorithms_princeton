public class BinaryInsertionSort {

    public int search(int[] arr, int startIndex, int endIndex, int key) {
        int lo = startIndex;
        int hi = endIndex;
        int mid = 0;
        while (lo <= hi) {
            mid = lo + (hi-lo)/2;

            if (key < arr[mid]) {
                hi = mid-1;
            } else if (key > arr[mid]) {
                lo = mid+1;
            } else {
                return mid;
            }
        }
        // Doesn't blindly return -1, but retain traversal info using mid
        return -mid-1;
    }

    public void sort(int[] arr) {
        int key;
        int n = arr.length-1;
        int j;

        for (int i = 1; i < n; i++) {
            key = arr[i];
            j =  Math.abs(search(arr,0, i, key) + 1);

            //while ()
        }
    }

    public static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {145, 78, -123, 3, 2, 50, 57, 0, 18, 69, 700};

        //BinaryInsertionSort bis = new BinaryInsertionSort();

        //bis.sort(arr);
        printArray(arr);
    }
}
