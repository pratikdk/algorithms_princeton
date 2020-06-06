public class Q2S3 {
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


    public static void printBucketColor(Comparable[] arr) {
        for (Comparable bucket: arr) {
            System.out.println(((PebbleBucket)bucket).getPebbleColor() + " ");
        }
    }


    public static void main (String[] args) {
        PebbleBucket[] buckets = new PebbleBucket[10];

        buckets[0] = new PebbleBucket("white");
        buckets[1] = new PebbleBucket("red");
        buckets[2] = new PebbleBucket("blue");
        buckets[3] = new PebbleBucket("white");
        buckets[4] = new PebbleBucket("white");
        buckets[5] = new PebbleBucket("red");
        buckets[6] = new PebbleBucket("blue");
        buckets[7] = new PebbleBucket("blue");
        buckets[8] = new PebbleBucket("red");
        buckets[9] = new PebbleBucket("red");

        sort(buckets);

        printBucketColor(buckets);
    }
}

class PebbleBucket implements Comparable<PebbleBucket>{
    String pebbleColor;
    int colorImp;

    public PebbleBucket(String color) {
        pebbleColor = color.toLowerCase();

        if (pebbleColor.equals("red")) {
            colorImp = 0;
        } else if (pebbleColor.equals("white")) {
            colorImp = 1;
        } else {
            colorImp = 2;
        }
    }

    public int compareTo(PebbleBucket ibucket) {
        if (this.colorImp < ibucket.colorImp) return -1;
        else if (this.colorImp > ibucket.colorImp) return 1;
        else return 0;
    }

    public String getPebbleColor() {
        return this.pebbleColor;
    }
}