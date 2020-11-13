package bearmaps.proj2d;

import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;
import bearmaps.proj2c.streetmap.Node;
import bearmaps.proj2c.streetmap.StreetMapGraph;

import java.lang.reflect.Array;
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

    private TrieST locations;
    private HashMap<String, List<String>> cleanedLocations;
    private HashMap<String, Node> stringsToNodes;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        List<Node> nodes = this.getNodes();

        nodesWithNeighbors = new HashMap<>();
        listOfPoints = new ArrayList<>();
        locations = new TrieST();
        cleanedLocations = new HashMap<>();
        stringsToNodes = new HashMap<>();

        for (Node node: nodes) {
            if(neighbors(node.id()).size() != 0) {
                Point nodePoint = new Point(node.lon(), node.lat());
                listOfPoints.add(nodePoint);
                nodesWithNeighbors.put(nodePoint, node.id());
            }

            //Gold Points
            String locationFullName = node.name();
            if (locationFullName != null) {
                String cleanedSearchName = cleanString(locationFullName);
                locations.put(cleanedSearchName, locationFullName);
                stringsToNodes.put(locationFullName, node);
                if (!cleanedLocations.containsKey(cleanedSearchName)) {
                    List<String> listOfDuplicates = new ArrayList<>();
                    listOfDuplicates.add(locationFullName);
                    cleanedLocations.put(cleanedSearchName, listOfDuplicates);
                } else {
                    cleanedLocations.get(cleanedSearchName).add(locationFullName);
                }
            }
        }
        weirdPointSet = new WeirdPointSet(listOfPoints);
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
        Iterable<String> strIter = locations.keysWithPrefix(cleanString(prefix));
        List<String> matchingPrefixLocations = new ArrayList<>();
        for (String prfx: strIter) {
            for (String s: cleanedLocations.get(prfx)) {
                matchingPrefixLocations.add(s);
            }
        }
        return matchingPrefixLocations;
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
        Iterable<String> strIter = locations.keysThatMatch(cleanString(locationName));
        List matchingLocations = new ArrayList<>();
        for (String locName: strIter) {
            for (String s: cleanedLocations.get(locName)) {
                Node node = stringsToNodes.get(s);
                HashMap<String, Object> stringsToObj = new HashMap<>();
                stringsToObj.put("lat", node.lat());
                stringsToObj.put("lon", node.lon());
                stringsToObj.put("name", node.name());
                stringsToObj.put("id", node.id());

                matchingLocations.add(stringsToObj);
            }
        }
        return matchingLocations;
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
