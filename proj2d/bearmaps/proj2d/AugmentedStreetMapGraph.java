package bearmaps.proj2d;

import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;
import bearmaps.proj2c.streetmap.Node;
import bearmaps.proj2c.streetmap.StreetMapGraph;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {

    private WeirdPointSet weirdPointSet;
    private List<Point> listOfPoints;
    private HashMap<Point, Long> nodesWithNeighbors;
    //private HashSet<String> locations;
    //private HashMap<String, String> namesMap;
    //private HashMap<String, Node> cleanedLocations;
    private KDTree kd;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        List<Node> nodes = this.getNodes();

        nodesWithNeighbors = new HashMap<>();
        listOfPoints = new ArrayList<>();
        //locations = new HashSet<>();
        //namesMap = new HashMap<>();
        //cleanedLocations = new HashMap<>();

        for (Node node: nodes) {
            if(neighbors(node.id()).size() != 0) {
                Point nodePoint = new Point(node.lon(), node.lat());
                listOfPoints.add(nodePoint);
                nodesWithNeighbors.put(nodePoint, node.id());
            }
            //attempt at Gold Points
            /**
            String fullLocationName = node.name();
            if (fullLocationName != null) {
                String cleanedSearchName = cleanString(fullLocationName);
                locations.add(cleanedSearchName);
                if (!namesMap.containsKey(cleanedSearchName)) {
                    namesMap.put(cleanedSearchName, fullLocationName);
                }
                if (!cleanedLocations.containsKey(cleanedSearchName)) {
                    cleanedLocations.put(cleanedSearchName, node);
                }
            }
             */
        }
        weirdPointSet = new WeirdPointSet(listOfPoints);
        //kd = new KDTree(listOfPoints);
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        return nodesWithNeighbors.get(weirdPointSet.nearest(lon, lat));
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        return new LinkedList<>();
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        return null;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
