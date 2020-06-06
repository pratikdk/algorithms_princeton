public class Q2S2 {
    public static boolean validatePermutation(Integer[] a1, Integer[] a2) {
        int n = a1.length;
        for (int i = 0; i < n; i++) {
            if (a1[i].compareTo(a2[i]) != 0) return false;
        }
        return true;
    }

    public static void printIntegerArray(Integer[] arr) {
        System.out.print("Array : ");
        for (int value: arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // sort and pass
        Integer[] arr1 = {4, 5, 1, 2, 3};
        Integer[] arr2 = {2, 1, 3, 5, 4};
        Integer[] arr3 = {1, 10, 20, 7, 8};

        ShellSort.sort(arr1);
        ShellSort.sort(arr2);
        ShellSort.sort(arr3);

        printIntegerArray(arr1);
        printIntegerArray(arr2);
        printIntegerArray(arr3);

        System.out.println("A1 == A2 " + validatePermutation(arr1, arr2));
        System.out.println("A1 == A3 " + validatePermutation(arr1, arr3));
    }
}

