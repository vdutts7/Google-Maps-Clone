package bearmaps;
import java.util.HashSet;
import java.util.List;

public class KDTree implements PointSet {
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;
    private Node kd;
    private HashSet<Point> pointsHashSet;

    private class Node {
        private Point p;
        private double x;
        private double y;
        private Node left;
        private Node right;
        private boolean orientation; //true for vertical, false for horizontal


        public Node(Point p, boolean orientation) {
            this.x = p.getX();
            this.y = p.getY();
            this.p = p;
            this.orientation = orientation;
            this.left = null;
            this.right = null;
        }
        
    }


    /**
     * Constructor method.
     * @param points list of Point objects
     */
    public KDTree(List<Point> points) {
        this.kd = null;
        pointsHashSet = new HashSet<>(points.size());
        for (Point point: points) {
            pointsHashSet.add(point);
            insert(point);
        }
    }

    private void insert(Point p) {
        this.kd = insertHelper(this.kd, p, kd.orientation);
    }

    private Node insertHelper(Node currentNode, Point pointAdded, boolean precedingOrientation) {
        if (currentNode == null) {
            return new Node(pointAdded, !precedingOrientation);
        } else {
            //if the currentNode's orientation is true AKA vertical
            if (currentNode.orientation) {
                if (currentNode.y > pointAdded.getY()) {
                    currentNode.left = insertHelper(currentNode.left, pointAdded, currentNode.orientation);
                } else {
                    currentNode.right = insertHelper(currentNode.right, pointAdded, currentNode.orientation);
                }
            }
            //if the currentNode's orientation is false AKA horizontal
            if (!currentNode.orientation) {
                if (currentNode.x > pointAdded.getX()) {
                    currentNode.left = insertHelper(currentNode.left, pointAdded, currentNode.orientation);
                } else {
                    currentNode.right = insertHelper(currentNode.right, pointAdded, currentNode.orientation);
                }
            }
        }
        return currentNode;
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

    private Node nearestHelper(Node n, Point target, Node best) {

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
