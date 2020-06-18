import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        // Check if points argument is null
        if (points == null || points.length < 1) {
            throw new IllegalArgumentException("Constructor received null or empty array of points.");
        }
        Point[] pointsCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("A null object encountered in array; Not supported!");
            }
            pointsCopy[i] = points[i];
        }
        Arrays.sort(pointsCopy);
        validateDuplicates(pointsCopy);

        ArrayList<LineSegment> segmentsList = new ArrayList<>();

        int p, q, r, s;
        for (p = 0; p < pointsCopy.length-3; ++p) {
            for (q = p+1; q < pointsCopy.length-2; ++q) {
                for (r = q+1; r < pointsCopy.length-1; ++r) {
                    for (s = r+1; s < pointsCopy.length; ++s) {
                        if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s]) &&
                            pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r])) {
                            LineSegment tempLineSegment = new LineSegment(pointsCopy[p], pointsCopy[s]);
                            if (!segmentsList.contains(tempLineSegment)) {
                                segmentsList.add(tempLineSegment);
                            }
                        }
                    }
                }
            }
        }
        segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);

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
        Point[] points = new Point[9];
        points[0] = new Point(1, 1);
        points[1] = new Point(5, 5);
        points[2] = new Point(4, 4);
        points[3] = new Point(7, 7);
        points[4] = new Point(4, 3);
        points[5] = new Point(6, 7);
        points[6] = new Point(8, 0);
        points[7] = new Point(6, 2);
        points[8] = new Point(5, 3);

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p: points) {
            p.draw();
        }

        StdDraw.show();
        drawPointsMatrix(points);

        Arrays.sort(points);
        StdOut.println("\nPrinting sorted array");
        for (Point p: points) {
            StdOut.println(p);
        }

        // print and draw the line segments
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        for (LineSegment segment : bcp.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

        Comparator<Point> p1Comparator = points[1].slopeOrder();
        StdOut.println("Comparing p2 and p3 with p1: " + p1Comparator.compare(points[2], points[3]));

    }
}
