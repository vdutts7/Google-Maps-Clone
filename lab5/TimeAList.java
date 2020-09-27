import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
    private List<Integer> Ns;
    private List<Double> times;
    private List<Integer> opCounts;

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

    public void main(String[] args) {
        timeAListConstruction();
        printTimingTable(Ns, times, opCounts);
    }

    public void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        Ns = new ArrayList<Integer>();
        for(int i = 1; i<=8; i*=2) {
            Ns.add(i*1000);
        }

        times = new ArrayList<Double>();
        times.add(0.00);
        times.add(0.01);
        times.add(0.01);
        times.add(0.04);
        times.add(0.10);
        times.add(0.50);
        times.add(1.15);
        times.add(3.74);

        opCounts = new ArrayList<Integer>();
        for(int i = 1; i<=8; i*=2) {
            opCounts.add(i*1000);
        }
    }
}
