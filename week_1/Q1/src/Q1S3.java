
public class Q1S3 {
    private int[] next_arr;
    private int[] size_arr;
    private int[] arr;

    public Q1S3(int N) {
        next_arr = new int[N];
        size_arr = new int[N];
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            //max_arr[i] = i;
            next_arr[i] = i;
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
        int i = findRoot(p);
        int j = findRoot(q);
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
            size_arr[maxLocal] += size_arr[minLocal];
        } else {
            arr[maxLocal] = minLocal;
            size_arr[minLocal] += size_arr[maxLocal];
        }
        next_arr[minLocal] = maxLocal;
    }

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

        System.out.println("\nnext_arr: ");
        for (int value : next_arr) {
            System.out.print(value + " ");
        }
    }

    public void remove(int idx) {
        if (idx < arr.length-1) {
            union(idx, idx+1);
        }
    }

    public int findSuccessor(int idx) {
        int nextValue = next_arr[findRoot(idx)];
        System.out.print("\nNext value w.r.t "+idx+": "+nextValue);
        return nextValue;
    }
}


class Q1S3Driver {
    public static void main(String[] args) {
        int n_elements = 10;

        Q1S3 q = new Q1S3(n_elements);
        q.printArrays();

        q.remove(2);
        q.remove(3);
        q.remove(4);

        q.findSuccessor(2);
        q.findSuccessor(4);

        q.remove(5);
        q.remove(8);
        q.remove(7);

        q.findSuccessor(4);
        q.findSuccessor(5);
        q.findSuccessor(7);
        q.findSuccessor(8);
        q.findSuccessor(6);

        q.remove(6);
        q.findSuccessor(4);
        q.findSuccessor(6);

        q.printArrays();
    }
}
