
public class Q1S2 {
    private int[] max_arr;
    private int[] size_arr;
    private int[] arr;

    public Q1S2(int N) {
        max_arr = new int[N];
        size_arr = new int[N];
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            max_arr[i] = i;
            size_arr[i] = 1;
            arr[i] = i;
        }
    }

    private int findRoot(int idx) {
        int initialIdx = idx;
        int tempIdx;
        while(arr[idx] != idx) {
            idx = arr[idx];
        }

        while(arr[initialIdx] != initialIdx) {
            tempIdx = initialIdx;
            initialIdx = arr[initialIdx];
            arr[tempIdx] = idx;
        }
        return idx;
    }

    public void union(int p, int q) {
        int i = findRoot(p); //1
        int j = findRoot(q); //2
        int maxLocal;
        int minLocal;
        if (i > j) {
            maxLocal = i;
            minLocal = j;
        } else {
            maxLocal = j;
            minLocal = i;
        }
        if (size_arr[minLocal] <= size_arr[maxLocal]) {
            arr[minLocal] = maxLocal;
            max_arr[maxLocal] = maxLocal; // No comparison with the existing value
            size_arr[maxLocal] += size_arr[minLocal];
        } else {
            arr[maxLocal] = minLocal;
            max_arr[minLocal] = maxLocal;
            size_arr[minLocal] += size_arr[maxLocal];
        }
    }


//    public void union(int p, int q) {
//        int i = findRoot(p); //2
//        int j = findRoot(q); //1
//        int maxLocal;
//        int minLocal;
//        if (i > j) {
//            maxLocal = i;
//            minLocal = j;
//        } else {
//            maxLocal = j;
//            minLocal = i;
//        }
//        if (size_arr[minLocal] < size_arr[maxLocal]) {
//            arr[maxLocal] = minLocal;
//            max_arr[maxLocal] = maxLocal;
//            size_arr[minLocal] += size_arr[maxLocal];
//        } else {
//            arr[minLocal] = maxLocal;
//            max_arr[maxLocal] = maxLocal;
//            size_arr[maxLocal] += size_arr[minLocal];
//        }
//    }

    public boolean connected(int p, int q) {
        boolean isConnected = findRoot(p) == findRoot(q);
        System.out.print("\nConnection ~ ("+p+", "+q+") : "+ isConnected);
        return isConnected;
    }

    public void printArrays() {
        System.out.println("\narr: ");
        for (int value : arr) {
            System.out.print(value + " ");
        }

        System.out.println("\nsize_arr: ");
        for (int value : size_arr) {
            System.out.print(value + " ");
        }

        System.out.println("\nmax_arr: ");
        for (int value : max_arr) {
            System.out.print(value + " ");
        }
    }

    public int find(int i) {
        int maxLocal = max_arr[findRoot(i)];
        System.out.print("\nMax value w.r.t "+i+": "+maxLocal);
        return maxLocal;
    }

}


class Q1S2Driver {
    public static void main(String[] args) {
        int n_elements = 10;

        Q1S2 q = new Q1S2(n_elements);
        q.printArrays();

        q.union(0, 2);
        q.union(1, 2);
        q.union(3, 5);
        q.union(5, 1);
        q.union(8, 9);

        q.printArrays();
        q.connected(5, 0);
        q.connected(2, 9);
        q.connected(5, 3);
        q.connected(4, 2);

        q.printArrays();

        q.find(1);
        q.find(8);
        q.find(4);
        q.find(3);
        q.find(5);
    }
}
