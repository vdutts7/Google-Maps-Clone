package bearmaps;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests for ArrayHeapMinPQ.java
 */
public class ArrayHeapMinPQTest {
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
     * Test for general functionality of ArrayHeapMinPQ.java add() and contains()
     * method in sample PQ.
     */
    @Test
    public void sanityAddContainsTest() {
        ArrayHeapMinPQ<String> employeesPQ = new ArrayHeapMinPQ<>();

        employeesPQ.add("CEO", 1);
        employeesPQ.add("Secondary Manager", 4);
        employeesPQ.add("Employee A", 5);
        employeesPQ.add("Vice President", 2);
        employeesPQ.add("Employee B", 6);
        employeesPQ.add("General Manager", 3);
        employeesPQ.add("Janitor", 7);

        assertTrue(employeesPQ.contains("CEO"));
        assertTrue(employeesPQ.contains("Vice President"));
        assertTrue(employeesPQ.contains("General Manager"));
        assertTrue(employeesPQ.contains("Secondary Manager"));
        assertTrue(employeesPQ.contains("Employee A"));
        assertTrue(employeesPQ.contains("Employee B"));
        assertTrue(employeesPQ.contains("Janitor"));

        assertFalse(employeesPQ.contains("CFO"));
    }

    /**
     * Test for general functionality of runtimes of checking contains() method for
     * large input sizes.
     */
    @Test
    public void containsRuntimeTest() {
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
            ArrayHeapMinPQ<Double> testPQ = new ArrayHeapMinPQ<>();

            for (int k = 0; k < sizeOfInput; k++) {
                Double randomItem = (double) k;
                Double randomItemPriority = (double) k;
                testPQ.add(randomItem, randomItemPriority);
            }
            Double testRandomDoubleContains = (double) StdRandom.uniform(Ns.get(j));
            Stopwatch sw = new Stopwatch();
            testPQ.contains(testRandomDoubleContains);
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);

            operations = sizeOfInput;
            opCounts.add(operations);
        }
        System.out.println("Timing table for ArrayHeapMinPQ contains()");
        printTimingTable(Ns, times, opCounts);

    }

    /**
     * Test for general functionality of runtimes of checking getSmallest() method for
     * large input sizes.
     */
    @Test
    public void getSmallestRuntimeTest() {
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
            ArrayHeapMinPQ<Double> testPQ = new ArrayHeapMinPQ<>();

            for (int k = 0; k < sizeOfInput; k++) {
                Double randomItem = (double) k;
                Double randomItemPriority = (double) k;
                testPQ.add(randomItem, randomItemPriority);
            }
            Stopwatch sw = new Stopwatch();
            testPQ.getSmallest();
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);

            operations = sizeOfInput;
            opCounts.add(operations);
        }
        System.out.println("Timing table for ArrayHeapMinPQ getSmallest()");
        printTimingTable(Ns, times, opCounts);

    }

    /**
     * Test for general functionality of runtimes of checking removeSmallest() method for
     * large input sizes.
     */
    @Test
    public void removeSmallestRuntimeTest() {
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
            ArrayHeapMinPQ<Double> testPQ = new ArrayHeapMinPQ<>();

            for (int k = 0; k < sizeOfInput; k++) {
                Double randomItem = (double) k;
                Double randomItemPriority = (double) k;
                testPQ.add(randomItem, randomItemPriority);
            }
            Stopwatch sw = new Stopwatch();
            testPQ.removeSmallest();
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);

            operations = sizeOfInput;
            opCounts.add(operations);
        }
        System.out.println("Timing table for ArrayHeapMinPQ removeSmallest()");
        printTimingTable(Ns, times, opCounts);

    }

    /**
     * Test for general functionality of runtimes of checking size() method for
     * large input sizes.
     */
    @Test
    public void sizeRuntimeTest() {
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
            ArrayHeapMinPQ<Double> testPQ = new ArrayHeapMinPQ<>();

            for (int k = 0; k < sizeOfInput; k++) {
                Double randomItem = (double) k;
                Double randomItemPriority = (double) k;
                testPQ.add(randomItem, randomItemPriority);
            }
            Stopwatch sw = new Stopwatch();
            testPQ.size();
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);

            operations = sizeOfInput;
            opCounts.add(operations);
        }
        System.out.println("Timing table for ArrayHeapMinPQ size()");
        printTimingTable(Ns, times, opCounts);

    }
}
