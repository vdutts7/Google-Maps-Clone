package bearmaps;
import java.util.List;

public class KDTree implements PointSet {
    private class Node {
        private Point p;
        private boolean orientation; //true for vertical, false for horizontal
        private static final boolean VERTICAL = true;
        private static final boolean HORIZONTAL = false;
        
    }
    /**
     * Constructor method.
     * @param points list of Point objects
     */
    public KDTree(List<Point> points) {

    }

    /**
     * @param x x-coordinate of input Point
     * @param y y-coordinate of input Point
     * @return closest point to the inputted coordinates
     * This should take \(O(\log N)\) time on average where \(N\) is the number of points.
     */
    @Override
    public Point nearest(double x, double y) {
        return null;
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);


        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
    }
}
