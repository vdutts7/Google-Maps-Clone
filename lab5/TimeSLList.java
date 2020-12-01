import java.util.ArrayList;
import java.util.List;
/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
    private static List<Integer> Ns;
    private static List<Double> times;
    private static List<Integer> opCounts;

    public static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        Ns = new ArrayList<>();
        times = new ArrayList<>();
        opCounts = new ArrayList<>();

        SLList<Integer> testSLList = new SLList<>();


        for (int i = 1; i < 5; i++) {
            Ns.add(i * 1000);
            for (int j = 0; j < i * 1000; j++ ) {
                testSLList.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int k = 0; k < 1000; k++) {
                testSLList.getLast();
            }
            opCounts.add(1000);
            times.add(sw.elapsedTime());
        }
        printTimingTable(Ns, times, opCounts);

    }

}
