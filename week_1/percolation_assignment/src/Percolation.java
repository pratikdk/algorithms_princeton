
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // Declare union-find data type
    private final WeightedQuickUnionUF q;
    // Row major array
    private final int[] gridActivations;
    private int openSitesCount;
    private final int n;
    private final int gridFlatLen;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size 'n' should be greater(>) than 0");
        }
        int i;
        this.n = n;
        openSitesCount = 0;

        // [n*n grid] + [2(top & bottom) virtual sites]
        gridFlatLen = (n*n)+2;

        // Initialize union-find data type
        q = new WeightedQuickUnionUF(gridFlatLen);
        // Initialize activation array
        gridActivations = new int[gridFlatLen];

        // Initialize values of grid
        for (i = 0; i < gridFlatLen; i++) { gridActivations[i] = 0; }

        // Reset last two sites as active; since we are treating them as virtual sites
        gridActivations[gridFlatLen-1] = 1;
        gridActivations[gridFlatLen-2] = 1;

        // Union/Connect first n rows to top virtual site (i.e grid[:n] -> grid[-2]) & last n rows to bottom site
        // and initialize validReceiver array
        for (i = 0; i < n; i++) {
            q.union(gridFlatLen-2, i);
        }
    }

    private void validateSiteIndex(int row, int col) {
        if ((row < 0 || row >= n) || (col < 0 || col >= n)) {
            throw new IllegalArgumentException("Either of 'row' or 'col' [i.e ("+row+","+col+")] is out of bounds");
        }
    }

    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            row -= 1;
            col -= 1;
            int siteIndex = ((n*row)+col);

            // Mark the site open
            gridActivations[siteIndex] = 1;
            openSitesCount += 1;

            // Connect current site to neighbouring opened sites
            // Pre-compute and store neighbour row,col indexes
            int rowAbove = row-1;
            int rowBelow = row+1;
            int colBefore = col-1;
            int colAfter = col+1;


            // Check for connection in all 4 directions
            if (rowAbove >= 0) { // Top Neighbour
                if (isOpen(rowAbove+1, col+1) && !(q.find(n*rowAbove+col) == q.find(n*row+col))) {
                    q.union(n*row+col, n*rowAbove+col);
                }
            }
            if (rowBelow < n) { // Bottom Neighbour
                if (isOpen(rowBelow+1, col+1) && !(q.find(n*rowBelow+col) == q.find(n*row+col))) {
                    q.union(n*row+col, n*rowBelow+col);
                }
            }
            else {
                // It's a bottom row, hook this site with bottom virtual site
                q.union(gridFlatLen-1, n*row+col);
            }
            if (colBefore >= 0) { // Left Neighbour
                if (isOpen(row+1, colBefore+1) && !(q.find(n*row+colBefore) == q.find(n*row+col))) {
                    q.union(n*row+col, n*row+colBefore);
                }
            }
            if (colAfter < n) { // Right Neighbour
                if (isOpen(row+1, colAfter+1) && !(q.find(n*row+colAfter) == q.find(n*row+col))) {
                    q.union(n*row+col, n*row+colAfter);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        row -= 1;
        col -= 1;
        validateSiteIndex(row, col);
        return gridActivations[((n*row)+col)] == 1;
    }

    public boolean isFull(int row, int col) {
        // validate site index and check if site is open
        if (isOpen(row, col)) {
            row -= 1;
            col -= 1;
            // check if site is connected with top virtual site
            return q.find(((n*row)+col)) == q.find(gridFlatLen-2);
        } else { return false; }
    }

    public int numberOfOpenSites() {
        return openSitesCount;
    }

    public boolean percolates() {
        // Checks if last two indices have same value (since we are treating them as virtual sites)
        return q.find(gridFlatLen-2) == q.find(gridFlatLen-1);
    }

//    public static void main(String[] args) {
//        // Nothing at all
//    }

    public void printGridStats() {
        System.out.println("\nFlat Activations: ");
        for (int value : gridActivations) {
            System.out.print(value+" ");
        }
    }

    public void printGrid() {
        System.out.print("\nGrid:\n");
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(q.find((i*n)+j)+ "\t");
            }
            System.out.println();
        }
    }


    public void printActivationGrid() {
        System.out.print("\nActivation Grid:\n");
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                //System.out.print(grid[]);(i*n)+j
                System.out.print(gridActivations[(i*n)+j]+"  ");
            }
            System.out.println();
        }
    }


    public static void main (String[] args) {
        int n = 5;
        Percolation p1 = new Percolation(n);
        p1.printGridStats();
        p1.printActivationGrid();
        p1.printGrid();
        System.out.println(p1.isOpen(1, 1));
        System.out.println(p1.isFull(1, 1));
        System.out.println(p1.numberOfOpenSites());
        System.out.println(p1.percolates());
        p1.open(1, 2);
        //p1.printActivationGrid();
        p1.printGrid();
        p1.open(2, 2);
        p1.printGrid();
        p1.open(3, 3);
        p1.printGrid();
        //p1.open(4, 3);
        p1.open(3, 2);
        p1.printGrid();
        p1.open(5, 2);
        p1.printGrid();
        p1.open(4, 2);
        p1.printGrid();
        p1.open(5, 3);
        p1.printGrid();
        p1.printActivationGrid();
        p1.printGrid();
        System.out.println("Is (4, 2) full: "+ p1.isFull(5, 2));
        System.out.println("Is (2, 2) full: "+ p1.isFull(2, 2));
        System.out.println("Percolates: "+ p1.percolates());
        System.out.println("Number of opensites: "+ p1.numberOfOpenSites());
    }
}
