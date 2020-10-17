package bearmaps;
import java.util.List;

/**
 * KDTree class. Implementation of a KDTree of points operating
 * in two subspaces: vertical (up and down directions) and horizontal (left and right directions).
 * Descriptions below for various method implementations.
 */
public class KDTree implements PointSet {
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;
    private Node kd;

    /**
     * Nested Node class. Nodes used as container for Point objects in KDTree.
     * Additionally, nodes provide means to store orientation of points in KDTree,
     * which alternate every level down the tree between vertical (up and down subspaces)
     * and horizontal (left and right subspaces).
     */
    public class Node {
        public Point p;
        public Node left;
        public Node right;
        //true for vertical, false for horizontal (see KDTree class variables for reference
        public boolean orientation;

        /**
         * Constructor for Node class.
         * @param p given point stored at that Node container
         * @param orientation orientation associated with the given point p
         */
        public Node(Point p, boolean orientation) {
            this.p = p;
            this.orientation = orientation;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Constructor method for KDTree.
     * @param points list of Point objects. Must be added to tree to assemble KDTree.
     */
    public KDTree(List<Point> points) {
        this.kd = null;
        for (Point point: points) {
            add(point);
        }
    }

    /**
     * Adds points to build tree, via use of Nodes. Keeps track of orientations as well.
     * @param pointAdded point from list of points to be added to tree
     */
    public void add(Point pointAdded) {
        if (this.kd == null) {
            this.kd = new Node(pointAdded, true);
        } else {
            this.kd = addHelper(this.kd, pointAdded, this.kd.orientation);
        }
    }

    /**
     * Recursive helper method for add method. Traverses depth of tree and inserts children
     * into appropriate locations with appropriate orientation, via use of Nodes.
     * @param currentNode the current node in the traversal process
     * @param pointAdded the point to be added to the tree
     * @param precedingOrientation tracker for  previous orientation in the traversal process to ensure
     *                             proper alternations
     * @return assembled tree, in form of Node with left and right children, all the way down to leaf nodes
     */
    private Node addHelper(Node currentNode, Point pointAdded, boolean precedingOrientation) {
        if (currentNode == null) {
            return new Node(pointAdded, !precedingOrientation);
        } else {
            if (precedingOrientation) {
                if (currentNode.p.getY() > pointAdded.getY()) {
                    currentNode.left = addHelper(currentNode.left, pointAdded, currentNode.orientation);
                } else {
                    currentNode.right = addHelper(currentNode.right, pointAdded, currentNode.orientation);
                }
            } else {
                if (currentNode.p.getX() > pointAdded.getX()) {
                    currentNode.left = addHelper(currentNode.left, pointAdded, currentNode.orientation);
                } else {
                    currentNode.right = addHelper(currentNode.right, pointAdded, currentNode.orientation);
                }
            }
        }
        return currentNode;
    }

    /**
     * @param xInput x-coordinate of input Point
     * @param yInput y-coordinate of input Point
     * @return closest point to the inputted coordinates
     * This should take \(O(\log N)\) time on average where \(N\) is the number of points.
     */
     @Override
    public Point nearest(double xInput, double yInput) {
         return nearestHelper(kd, new Point(xInput, yInput), kd).p;
    }

    /**
     * Recursive helper method for nearest method. Traverses depth of tree and searches
     * for nearest point to input point.
     * @param node the current node in the traversal process
     * @param inputPoint the point being compared
     * @param currentBest tracks current nearest point
     * @return the Node associated with nearest Point
     */
    private Node nearestHelper(Node node, Point inputPoint, Node currentBest) {
        if (node == null) {
            return currentBest;
        } else {
            if (Point.distance(currentBest.p, inputPoint) > Point.distance(node.p, inputPoint)) {
                currentBest = node;
            }
            Node goodChild;
            Node badChild;

            /**
             * chooseChild():
             * true represents goodChild being left child, badChild being right child
             * false represents goodChild being right child, badChild being left child
             */
            if (chooseChild(node, inputPoint)) {
                goodChild = node.left;
                badChild = node.right;
            } else {
                goodChild = node.right;
                badChild = node.left;
            }
            currentBest = nearestHelper(goodChild, inputPoint, currentBest); //handles the goodChild

            //Code block below handles/explores the badChild
            if (node.orientation) {
                if (closerThanCurrentBest(node, inputPoint, currentBest)) {
                    currentBest = nearestHelper(badChild, inputPoint, currentBest);
                }
            } else {
                if (closerThanCurrentBest(node, inputPoint, currentBest)) {
                    currentBest = nearestHelper(badChild, inputPoint, currentBest);
                }

            }
        }
        return currentBest;
    }

    /**
     * Private helper method to determine which child (good or bad) is on which side (left or right).
     * @param node the node currently being dealt with
     * @param inputPoint the point we eventually wish to return in nearest() associated with nearest node
     * @return boolean with following equivalents:
     *                           true represents goodChild being left child, badChild being right child
     *                           false represents goodChild being right child, badChild being left child
     */
    private boolean chooseChild(Node node, Point inputPoint) {
        if (node.orientation) {
            return Math.floor(inputPoint.getY() - node.p.getY()) < 0;
        } else {
            return Math.floor(inputPoint.getX() - node.p.getX()) < 0;
        }
    }

    /**
     * Private helper method to determine if given point is closer than the current best.
     * @return boolean true/false indicating if given point is closer than the current best
     */
    private boolean closerThanCurrentBest(Node node, Point goalPoint, Node currentBest) {
        if (node.orientation) {
            Point boundaryPointVertical = new Point(goalPoint.getX(), node.p.getY());
            if (Point.distance(boundaryPointVertical, goalPoint) < Point.distance(currentBest.p, goalPoint)) {
                return true;
            }
            return false;
        } else {
            Point boundaryPointHorizontal = new Point(node.p.getX(), goalPoint.getY());
            if (Point.distance(boundaryPointHorizontal, goalPoint) < Point.distance(currentBest.p, goalPoint)) {
                return true;
            }
            return false;
        }
    }

    /**
     * Main method used for testing purposes of KDTree.java implementation.
     * @param args
     */
    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(40, 26);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree myKdTree = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        System.out.println(myKdTree.nearest(400, 200));
    }
}
