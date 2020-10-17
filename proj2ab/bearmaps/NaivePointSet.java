package bearmaps;
import java.util.List;

/**
 * Naive, linear-time  solution to solve the problem of
 * finding the closest point to a given coordinate.
 * To be used to easily verify if
 * the result of k-d tree’s nearest method is correct.
 */
public class NaivePointSet implements PointSet {
    List<Point> points;
    /**
     * Constructor method.
     * @param points list of Point objects
     */
    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    /**
     * @param x x-coordinate of input Point
     * @param y y-coordinate of input Point
     * @return closest point to the inputted coordinates
     * This should take \(\Theta(N)\) time where \(N\) is the number of points.
     */
    @Override
    public Point nearest(double x, double y) {
        double minDistance = 10000;
        Point closestPoint = null;
        for (Point p : points) {
            double distance = Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = p;
            }
        }
        return closestPoint;
    }

    /**
     * Main method used for testing purposes.
     * @param args
     */
    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4

    }
}
