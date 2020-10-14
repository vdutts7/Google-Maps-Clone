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
        for (Point p: points) {
            double distance = Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = p;
            }
        }
        return closestPoint;
}
