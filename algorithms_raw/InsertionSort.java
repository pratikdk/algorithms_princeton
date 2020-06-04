
public class InsertionSort {
    public void sort(int[] arr) {
        int key;
        int n = arr.length;
        int i, j;
        for (i = 1; i < n; i++) {
            key = arr[i];
            j = i-1;

            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }

    void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};

        InsertionSort is = new InsertionSort();
        is.sort(arr);

        is.printArray(arr);
    }
}
