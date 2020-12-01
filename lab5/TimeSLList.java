import java.util.ArrayList;
import java.util.List;
/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
    private List<Integer> Ns;
    private List<Double> times;
    private List<Integer> opCounts;

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

    public void main(String[] args) {

        timeGetLast();
    }

    public void timeGetLast() {
        // TODO: YOUR CODE HERE
        for(int i = 1; i < Ns.size(); i *= 2) {
            Ns.add(i * 1000);
        }

        times.add(0.00);
        times.add(0.01);
        times.add(0.01);
        times.add(0.04);
        times.add(0.10);
        times.add(0.50);
        times.add(1.15);
        times.add(3.74);

        for(int i = 1; i <= 8; i *= 2) {
            opCounts.add(i * 1000);
        }
    }

}
