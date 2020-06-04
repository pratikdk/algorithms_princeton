public class BinarySearch {

    public int search(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length-1;
        int mid;
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
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 4, 10, 40 };
        int[] arr2 = {23, 37};

        BinarySearch bs = new BinarySearch();

        System.out.println("Element 10 index: " +  bs.search(arr,10));
        System.out.println("Element 10 index: " +  bs.search(arr,40));
        System.out.println("Element 10 index: " +  bs.search(arr,2));
        System.out.println("Element 10 index: " +  bs.search(arr2,37));
    }
}
