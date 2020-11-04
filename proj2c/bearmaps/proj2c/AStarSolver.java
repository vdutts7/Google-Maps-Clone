package bearmaps.proj2c;

import java.util.*;

import edu.princeton.cs.algs4.Stopwatch;


public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private DoubleMapPQ<Vertex> pq;
    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, Vertex> edgeTo;
    private HashSet<Vertex> alreadyVisited;
    private SolverOutcome outcome;
    private List<Vertex> solutions;
    private double solutionWeight;
    private int numStatesExplored;
    private double explorationTime;


    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        pq = new DoubleMapPQ<Vertex>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        alreadyVisited = new HashSet<>();
        solutions = new ArrayList<Vertex>();

        numStatesExplored = 0;
        pq.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);
        edgeTo.put(start, null);

        while (pq.size() > 0) {
            if (sw.elapsedTime() >timeout) {
                outcome = SolverOutcome.TIMEOUT;
                break;
            }
            if (pq.getSmallest().equals(end)) {
                outcome = SolverOutcome.SOLVED;
                break;
            }

            Vertex v = pq.removeSmallest();
            numStatesExplored += 1;

            List<WeightedEdge<Vertex>> neighbors = input.neighbors(v);
            for (int i = 0; i < neighbors.size(); i++) {
                WeightedEdge<Vertex> currEdge = neighbors.get(i);
                relaxEdges(currEdge, input, end);
            }
        }
        solutionWeight = distTo.getOrDefault(end, 0.0);
        if (outcome == SolverOutcome.SOLVED) {
            while (!end.equals(start)) {
                solutions.add(end);
                end = edgeTo.get(end);
            }
            solutions.add(start);
            reverseList(solutions);
        }
        if (outcome != SolverOutcome.TIMEOUT && outcome != SolverOutcome.SOLVED) {
            outcome = SolverOutcome.UNSOLVABLE;
        }
        explorationTime = sw.elapsedTime();
    }

    private void reverseList(List<Vertex> vertexList) {
        if (vertexList == null) {
            return;
        }
        if (vertexList.size() < 2) {
            return;
        }
        Vertex vertex = vertexList.remove(0);
        reverseList(vertexList);
        vertexList.add(vertex);
    }

    private void relaxEdges(WeightedEdge<Vertex> edge, AStarGraph<Vertex> input, Vertex goalVertex) {
        Vertex v = edge.from();
        Vertex w = edge.to();

        if (!distTo.containsKey(w) || distTo.get(v) + edge.weight() < distTo.get(w)) {
            distTo.put(w, distTo.get(v) + edge.weight());
            edgeTo.put(w, v);

            if (pq.contains(w)) {
                pq.changePriority(w, distTo.get(w) + input.estimatedDistanceToGoal(w, goalVertex));
            } else {
                pq.add(w, distTo.get(w) + input.estimatedDistanceToGoal(w, goalVertex));
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solutions;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return explorationTime;
    }
}
