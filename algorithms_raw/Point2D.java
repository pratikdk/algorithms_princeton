public class Point2D implements Comparable<Point2D>{
    private double xValue;
    private double yValue;
    // Euclidean distance from origin
    private double distance;

    public Point2D(double x, double y) {
        xValue = x;
        yValue = y;
        distance = Math.sqrt(Math.pow(xValue, 2) + Math.pow(yValue, 2));
    }

    public int compareTo(Point2D that) {
        if (this.distance < that.distance) return -1;
        else if (this.distance > that.distance) return 1;
        else return 0;
    }
}
