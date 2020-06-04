public class Q2S1 {

    public void get3Sum(int[] arr) {
        int n = arr.length;
        int i, j;
        int searchVal;
        int searchedIndex;
        BinarySearch bs = new BinarySearch();

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                searchVal = -(arr[i] + arr[j]);
                searchedIndex = bs.search(arr, searchVal);

                if (searchedIndex > -1) {
                    System.out.println(arr[i] + " + " + arr[j] + " + " + arr[searchedIndex] + " = 0");
                }
            }
        }
    }

    public static void main (String[] args) {
        int[] arr = {20, 40, 18, 20, 30, 50, 0, 2, -20, -10, -30, -40, -50};

        InsertionSort is = new InsertionSort();
        is.sort(arr);
        is.printArray(arr);

        Q2S1 q2s1 = new Q2S1();
        q2s1.get3Sum(arr);
    }

}

