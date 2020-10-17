package bearmaps;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;
import org.junit.Test;

/**
 * Tests for KDTree.java
 */
public class KDTreeTest {
    private List<Integer> Ns;
    private List<Double> times;
    private List<Integer> opCounts;

    /**
     * Prints timing table for each test.
     * @param Ns N values of table.
     * @param times stopwatch times.
     * @param opCounts operation counts.
     */
    private void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    /**
     * Test for general functionality of runtimes of adding of large numbers of points to
     * KDTrees, as per KDTree.java.
     */
    @Test
    public void functionalityAddTest() {
        Random prng = new Random();
        prng.setSeed(12345L);

        Ns = new ArrayList<>();
        int[] arr = new int[] {31250, 62500, 125000, 250000, 500000, 1000000, 2000000};
        for (int i: arr) {
            Ns.add(i);
        }
        times = new ArrayList<>();
        opCounts = new ArrayList<>();

        for (int j = 0; j < Ns.size(); j++) {
            int sizeOfInput = Ns.get(j);
            int operations = 0;
            ArrayList<Point> points = new ArrayList<Point>();
            for (int k = 0; k < sizeOfInput; k++) {
                Point randomPoint = new Point(prng.nextInt(), prng.nextInt());
                points.add(randomPoint);
            }
            Stopwatch sw = new Stopwatch();
            KDTree kd = new KDTree(points);

            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);

            operations = sizeOfInput;
            opCounts.add(operations);
        }
        System.out.println("Timing table for Kd-Tree Construction");
        printTimingTable(Ns, times, opCounts);
    }

    /**
     * Test for general functionality of runtimes of checking KDTree's nearest method for
     * large numbers of points, as per KDTree.java.
     */
    @Test
    public void functionalityNearestTest() {
        Random prng = new Random();
        prng.setSeed(12345L);

        Ns = new ArrayList<>();
        int[] arr = new int[] {31250, 62500, 125000, 250000, 500000, 1000000, 2000000};
        for (int i: arr) {
            Ns.add(i);
        }
        times = new ArrayList<>();
        opCounts = new ArrayList<>();

        for (int j = 0; j < Ns.size(); j++) {
            int sizeOfInput = 1000000;
            int operations = 0;
            ArrayList<Point> points = new ArrayList<Point>();
            for (int k = 0; k < sizeOfInput; k++) {
                Point randomPoint = new Point(prng.nextInt(), prng.nextInt());
                points.add(randomPoint);
            }

            Stopwatch sw = new Stopwatch();
            KDTree kd = new KDTree(points);
            kd.nearest(prng.nextInt(), prng.nextInt());

            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);

            operations = sizeOfInput;
            opCounts.add(operations);
        }
        System.out.println("Timing table for Kd-Tree Nearest");
        printTimingTable(Ns, times, opCounts);
    }

    /**
     * Test for general functionality of runtimes of checking NaivePointSet's nearest method for
     * large numbers of points, as per NaivePointSet.java.
     */
    @Test
    public void functionalityNearestTestNaivePointSet() {
        Random prng = new Random();
        prng.setSeed(12345L);

        Ns = new ArrayList<>();
        int[] arr = new int[] {31250 / 1000, 62500 / 1000, 125000 / 1000, 250000 / 1000,
                500000 / 1000, 1000000 / 1000, 2000000 / 1000};
        for (int i: arr) {
            Ns.add(i);
        }
        times = new ArrayList<>();
        opCounts = new ArrayList<>();

        for (int j = 0; j < Ns.size(); j++) {
            int sizeOfInput = 1000000;
            int operations = 0;
            ArrayList<Point> points = new ArrayList<Point>();
            for (int k = 0; k < sizeOfInput; k++) {
                Point randomPoint = new Point(prng.nextInt(), prng.nextInt());
                points.add(randomPoint);
            }

            Stopwatch sw = new Stopwatch();
            NaivePointSet nn = new NaivePointSet(points);
            nn.nearest(prng.nextInt(), prng.nextInt());

            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);

            operations = sizeOfInput;
            opCounts.add(operations);
        }
        System.out.println("Timing table for Naive Nearest");
        printTimingTable(Ns, times, opCounts);
    }

    /**
     * Main method for KDTreeTests. Comment out tests not used to independently assess each test and prevent
     * stopwatch interference.
     * @param args
     */
    public static void main(String[] args) {
        KDTreeTest kdTreeTest = new KDTreeTest();
        //kdTreeTest.functionalityAddTest();
        //kdTreeTest.functionalityNearestTestNaivePointSet();
        kdTreeTest.functionalityNearestTest();
    }
}