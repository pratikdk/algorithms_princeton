public class Q2S2 {
}


import edu.princeton.cs.algs4.Stopwatch;
public class BitonicArray {

    private static boolean bitonicSearch(int[] array, int start, int end, int target, boolean desc) {
        int mid = (start + end) / 2;

        if (array[mid] == target) {
            return true;
        }

        if (start > end) {
            return false;
        }

        if ((desc && array[mid] < target) || (!desc && array[mid] > target)) {
            return bitonicSearch(array, start, mid-1, target, desc);
        } else {
            return bitonicSearch(array, mid+1, end, target, desc);
        }
    }


    // Return Index of Peak Entry in the Array 0.031s
    private static int binarySearchPeak(int[] array, int start, int end) {
        int mid = (start + end) / 2;

        if (start == end) {
            return mid;
        } else if (array[mid] < array[mid+1]) {
            return binarySearchPeak(array, mid+1, end);
        } else {
            return binarySearchPeak(array, start, mid);
        }
    }

//    // My version without recursion(Strong assumption of Bitonic array) 0.031s
//    private static int binarySearchPeak(int[] array, int start, int end) {
//        int mid = (start + end) / 2;
//
//        if (start == end) {
//            return mid;
//        } else if (array[mid] < array[mid+1]) {
//            return mid+1;
//        } else {
//            return mid;
//        }
//    }

    private static boolean find(int[] array, int peak, int target) {
        return (bitonicSearch(array, 0, peak, target, false) ||
                bitonicSearch(array, peak, array.length-1, target, true));
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        int[] array = { 1, 2, 3, 4, 5, 15, 10, 9, 8, 7, 6 };
        int peak = binarySearchPeak(array, 0, array.length-1);

        int target = 0;
        System.out.println(target + ": " + find(array, peak, target));
        System.out.println(stopwatch.elapsedTime());

        target = 1;
        System.out.println(target + ": " + find(array, peak, target));

        target = 8;
        System.out.println(target + ": " + find(array, peak, target));

        target = 20;
        System.out.println(target + ": " + find(array, peak, target));

        target = 10;
        System.out.println(target + ": " + find(array, peak, target));
    }
}