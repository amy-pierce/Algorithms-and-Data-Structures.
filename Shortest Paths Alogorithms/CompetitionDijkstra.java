
import java.io.FileInputStream;
import java.util.*;

/**
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *  Each contestant walks at a given estimated speed.
 *  The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 * This class implements the competition using Dijkstra's algorithm
 */

/**
 * 
 * @author pierceam
 *
 */

class CompetitionDijkstra {

    int sA, sB, sC;
    int slowest;

    String filename;

    private TreeMap<Integer, myNode> graph;

    
    
    private class myNode {
        int id;
        double cost = Double.MAX_VALUE; 
        ArrayList<myPath> paths = new ArrayList<>();

        myNode(int id) {
            this.id = id;
        }

        void addToPath(myNode node, double cost) {
            paths.add(new myPath(node, cost));
        }
    }

    private class myPath {
        myNode destination;
        double cost;

        myPath(myNode destination, double cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA,sB,sC: speeds for 3 contestants
     */
    CompetitionDijkstra(String filename, int sA, int sB, int sC) {
        this.filename = filename;
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
        this.readFile();
    }

    private void readFile() {

        slowest = Math.min(sA, sB);
        slowest = Math.min(slowest, sC);
        if (filename == null) slowest = -1;
        graph = new TreeMap<>();

        try {
            Scanner newScanner = new Scanner(new FileInputStream(filename));
            int Vertex = newScanner.nextInt();
            int Edge = newScanner.nextInt();
            for (int indexi = 0; indexi < Edge; indexi++) {
                if (newScanner.hasNext()) {
                    int intersectA = newScanner.nextInt();
                    int intersectB = newScanner.nextInt();
                    double length = newScanner.nextDouble() * 1000;
                    myNode nodeA, nodeB;

                    if (graph.get(intersectA) == null) {
                        nodeA = new myNode(intersectA);
                        graph.put(intersectA, nodeA);
                    } else nodeA = graph.get(intersectA);

                    if (graph.get(intersectB) == null) {
                        nodeB = new myNode(intersectB);
                        graph.put(intersectB, nodeB);
                    } else nodeB = graph.get(intersectB);

                    nodeA.addToPath(nodeB, length);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            slowest = -1;
        }
  
    }

    private double shortestPath(int start) {

        LinkedList<myNode> nodes = new LinkedList<>();
        for (myNode node : graph.values()) {
            if (node.id == start) node.cost = 0;
            else node.cost = Double.MAX_VALUE;
            nodes.add(node);
        }

        for (int indexi = 0; indexi < graph.values().size(); indexi++) {
            for (myNode node : nodes) {
                for (myPath path : node.paths) {
                    double currentCost = node.cost + path.cost;
                    if (currentCost < path.destination.cost) {
                        path.destination.cost = currentCost;
                    }
                }
            }
        }

        double max = Double.MIN_VALUE;
        for (myNode node : graph.values()) {
            if (node.cost == Double.MAX_VALUE) return node.cost;
            else if (node.cost > max)
                max = node.cost;
        }
        return max;
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition() {
        if((sA > 100 || sA < 50) || (sB > 100 || sB < 50) || (sC > 100 || sC < 50)){
            return -1;
        }

        if (graph.size() == 0 || slowest <= 0) return -1;
        double maxDist = -1;
        for (myNode node : graph.values()) {
            double dist = shortestPath(node.id);
            if (dist == Double.MAX_VALUE) return -1;
            maxDist = Math.max(maxDist, dist);
        }
        return (int) Math.ceil(maxDist / slowest);
    }


}