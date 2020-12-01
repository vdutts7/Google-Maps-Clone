import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
    private static List<Integer> Ns;
    private static List<Double> times;
    private static List<Integer> opCounts;

    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
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

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        Ns = new ArrayList<>();
        times = new ArrayList<>();
        opCounts = new ArrayList<>();

        AList<Integer> testAList = new AList<>();

        for (int i = 1; i < 8; i++) {
            Ns.add((int) Math.pow(2, i) * 1000);
            for (int j = 0; j < i * 1000; j++ ) {
                testAList.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int k = 0; k < 1000; k++) {
                testAList.getLast();
            }
            opCounts.add(1000);
            times.add(sw.elapsedTime());
        }
        printTimingTable(Ns, times, opCounts);
    }
}
