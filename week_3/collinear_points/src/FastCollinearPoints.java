import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        // Check if points argument is null
        if (points == null || points.length < 1) {
            throw new IllegalArgumentException("Constructor received null or empty array of points.");
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("A null object encountered in array; Not supported!");
            }
        }
        validateDuplicates(points);

        Arrays.sort(points);
        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        StdOut.println();
        for (int k = 0; k < points.length; k++) {
            StdOut.println(points[k] +  ", " + pointsCopy[k]);
        }

        ArrayList<LineSegment> segmentsList = new ArrayList<>();

        int head;
        int i, j, k, l;
        Point low;
        Point high;
        for (i = 0; i < points.length; i++) {
            StdOut.print("\ni ----> " + i + ", point: " + points[i]);
            Arrays.sort(pointsCopy, points[i].slopeOrder());
            StdOut.println();
            for (k = 0; k < pointsCopy.length; k++) {
                StdOut.println(pointsCopy[k]);
            }

            head = 0;
            for (j = 0; j < pointsCopy.length-1; j=head) {
                StdOut.print("\n>> j ----> " + j + " ["+ pointsCopy[j] +"]");
                if (points[i].slopeTo(pointsCopy[j]) == points[i].slopeTo(pointsCopy[j+1])) {
                    head += 1;
                    while (points[i].slopeTo(pointsCopy[j]) == points[i].slopeTo(pointsCopy[head+1])) {
                        head += 1;
                    }

                    if (points[i].compareTo(pointsCopy[head]) != 0 && (head-j) >= 2) {
                        StdOut.println("Found: i: " + points[i] + ", head: " + pointsCopy[head] + ", [head-j] = " + head + "-" + j + "= " + (head-j));
                        LineSegment tempLineSegment;
                        low = pointsCopy[head];
                        high = pointsCopy[head];
                        for (l = head; l > j ; --l) {
                            if (high.compareTo(pointsCopy[l-1]) < 0) {
                                high = pointsCopy[l-1];
                            }
                            if (low.compareTo(pointsCopy[l-1]) > 0) {
                                low = pointsCopy[l-1];
                            }
                        }
                        StdOut.println("Low: " + low + ", high: " + high);
                        if (points[i].compareTo(low) < 0) {
                            low = points[i];
                        } else if (points[i].compareTo(high) > 0) {
                            high = points[i];
                        }

                        tempLineSegment = new LineSegment(low, high);
                        StdOut.println("Low: " + low + ", high: " + high);

                        if (!segmentsList.contains(tempLineSegment)) {
                            StdOut.println("Contains: " + tempLineSegment);
                            StdOut.println(Arrays.toString(segmentsList.toArray()));
                            segmentsList.add(tempLineSegment);
                        }
                    }
                } else {
                    head++;
                }
                low = null;
                high = null;
            }
            StdOut.println();
        }
        segmentsList = removeDuplicates(segmentsList);
        segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);

    }


    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
        ArrayList<T> newList = new ArrayList<T>();
        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    private static void validateDuplicates(Point[] pts) {
        // The assumption: equal points have both axis similar
        for (int i = 0; i < pts.length-1; i++) {
            if (pts[i].compareTo(pts[i+1]) == 0) {
                throw new IllegalArgumentException("Duplicated entries in given points.");
            }
        }
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }

    public static void drawPointsMatrix(Point[] points) {
        int matrix_size = 10;
        String[] pointMatrix = new String[matrix_size * matrix_size];
        StdOut.println("Size: " + pointMatrix.length);

        for (Point p: points) {
            pointMatrix[(matrix_size*p.getY()) + p.getX()] = "  X";

        }

        for (int i = pointMatrix.length-1; i >= 0; i--) {
            int pointIndex = 0;
            pointIndex = matrix_size - (i % matrix_size) - 1;
            pointIndex = (i - (matrix_size-1)) + 2*pointIndex;

            if (pointMatrix[pointIndex] == null) {
                StdOut.print("  -");
            } else {
                StdOut.print(pointMatrix[pointIndex]);
            }
            if (i>0 && (i % (matrix_size)) == 0) {
                StdOut.println();
            }
        }
    }

    public static void main(String[] args) {
        // Read file using filename
//        In in = new In(args[0]);
//        // Extract point counts from first line in file
//        int n = in.readInt();
//        // Initialize Points array with the obtained point counts
//        Point[] points = new Point[n];
//        // Iteratively extract x y points from each line, initialize points array
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }
        Point[] points = new Point[10];
        points[0] = new Point(1, 1);
        points[1] = new Point(5, 5);
        points[2] = new Point(4, 4);
        points[3] = new Point(7, 7);
        points[4] = new Point(4, 3);
        points[5] = new Point(6, 7);
        points[6] = new Point(8, 0);
        points[7] = new Point(6, 2);
        points[8] = new Point(5, 3);
        points[9] = new Point(9, 1);

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p: points) {
            p.draw();
        }

        StdDraw.show();
        drawPointsMatrix(points);
//
//        Merge.sort(points);
//        Arrays.sort(points);
//        StdOut.println("\nPrinting sorted array");
//
//        for (Point p: points) {
//            StdOut.println(p);
//        }

        // print and draw the line segments
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        StdOut.println("\nNumber of segments: " +  fcp.numberOfSegments());

        for (LineSegment segment : fcp.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

//        StdOut.println("\nPrinting slope sorted array w.r.t: " + points[0]);
//        Arrays.sort(points, points[0].slopeOrder());
//        StdOut.println();
//        for (Point p: points) {
//            StdOut.println(p);
//        }
    }
}
