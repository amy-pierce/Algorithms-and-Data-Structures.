import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
/**
 * 
 * @author pierceam
 *
 */
/*Theory
 *  * Performance Discussion
 *
 * Question 1: Justify the choice of the data structures used in CompetitionDijkstra and CompetitionFloydWarshall
 * Answer 1:
 * CompetitionDijkstra-I used a treemap to represent the dijkstra graph. The treemap is made up of nodes with a list 
 * of the paths that extend from it. I used it because after looking at other data structures, I found that a treemap 
 * would be a good choice due to it being extensible, that is it can grow and expand
 * 
 * CompetitionFloydWarshall- I used a 2D-array to represent the FW graph. I chose this as a 2D-array is basically a matrix 
 * which is what the FW algo is based on. The structure of the array is [node from][destination node] and the size
 * is vertex*vertex and any empty nodes are defaulted to infinity
 * 
 *
 *
 * Question 2. Explain theoretical differences in the performance of Dijkstra and Floyd-Warshall algorithms in the
 * given problem. Also explain how would their relative performance be affected by the density of the graph.
 * Which would you choose in which set of circumstances and why?
 *Answer 2:
 *Performances:
 *Dijkstra-the performance is performance Big-O(N^2 E)(where n is the number of nodes/vertex  and E is the number of Edges in graph). 
 * Dijkstra is usually more efficient for a single source graph but because we are asked to find the shortest path
 * from all nodes, we must treat all the nodes as a source at some point in the algorithm which will effect the performance
 *   *
 *  Floyd Warshall-the performance is Big-O(N^3)(where v is the number or nodes/vertex in graph). FWgenerates finds the 
 *  shortest path from all sources to all destinations, which was ideal for this assignment. The nested for loops make the performance N^3 as each loop 
 *  runs N iterations (N=number of nodes in graph).
 *
 *My choice:
 *I would choose Dijkstra based on the fact that it does not waste memory on edges that are non existent, which would be the case
 *in a sparse/bare graph and its performance is based on BigO( (N^2)*E) (where N is the number of nodes/vertex and E is the number of Edges)
 *If the graph was more dense, then FW would be my preferred algorithm as it is based on number of nodes/vertex and
 *not the number of edges and E (edges)does not effect the performance of FW and the 2D-array would by used effieciently
 *rather than having a lot of 'infinity nodes'
 *
 *  Basically my choice depends on the graph density. I would choose Dijkstra for a sparse graph and FW for a dense graph.
 * 
 */
public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
        CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 80, 60);
        assertEquals("constructor failed with valid input", dijkstra.slowest, 50);
    }

    @Test
    public void testDijkstra() {
        CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50,80,60);
        assertEquals("Test competition with TINYEWD", 38, dijkstra.timeRequiredforCompetition());

         dijkstra = new CompetitionDijkstra("tinyEWD.txt", 10,80,60);
        assertEquals("Test competition with TINYEWD", -1, dijkstra.timeRequiredforCompetition());

         dijkstra = new CompetitionDijkstra("TINYsdfgdfgEWD.txt", 50, 80, 60);
        assertEquals("Test competition with invalid filename", -1, dijkstra.timeRequiredforCompetition());
        
        dijkstra = new CompetitionDijkstra(null, 50, 80, 60);
        assertEquals("Test competition with invalid filename", -1, dijkstra.timeRequiredforCompetition());

        
         dijkstra = new CompetitionDijkstra("tinyEWD.txt", -1, 80, 60);
        assertEquals("Test  with negative speed", -1, dijkstra.timeRequiredforCompetition());

         dijkstra = new CompetitionDijkstra(null, 50, 80, 60);
        assertEquals("Test  with null filename", -1, dijkstra.timeRequiredforCompetition());

         dijkstra = new CompetitionDijkstra("tinyEWD-2.txt", 50, 80, 60);
        assertEquals("Test  with node that doesn't have path", -1, dijkstra.timeRequiredforCompetition());

         dijkstra = new CompetitionDijkstra("input-J.txt", 98, 70, 84);
        assertEquals("Test  with speeds fine?", -1, dijkstra.timeRequiredforCompetition());

         dijkstra = new CompetitionDijkstra("tinyEWD.txt", 5, 80, 60);
        assertEquals("Test  with less than 50 speed", -1, dijkstra.timeRequiredforCompetition());
    }



    @Test
    public void testFWConstructor() {
        CompetitionFloydWarshall FW = new CompetitionFloydWarshall("input-I.txt", 60,70,84);
        assertEquals("constructor failed with valid input", FW.slowestPath, 60);
    }

    @Test
    public void testFW() {
        CompetitionFloydWarshall FW = new CompetitionFloydWarshall("tinyEWD.txt", 50,80,60);
        assertEquals("Test  with TINYEWD", 38, FW.timeRequiredforCompetition());

         FW = new CompetitionFloydWarshall("fakename.txt", 50, 80, 60);
        assertEquals("Test with invalid filename", -1, FW.timeRequiredforCompetition());

         FW = new CompetitionFloydWarshall("tinyEWD.txt", -1, 80, 60);
        assertEquals("Test  with negative speed", -1, FW.timeRequiredforCompetition());

        FW = new CompetitionFloydWarshall("tinyEWD.txt", 10, 130, 60);
        assertEquals("Test  with negative speed", -1, FW.timeRequiredforCompetition());

        FW = new CompetitionFloydWarshall(null, -1, 80, 60);
        assertEquals("Test  with negative speed", -1, FW.timeRequiredforCompetition());

        
         FW = new CompetitionFloydWarshall(null, 50, 80, 60);
        assertEquals("Test  with null filename", -1, FW.timeRequiredforCompetition());

        FW = new CompetitionFloydWarshall("tinyEWD-2.txt", 50, 80, 60);
        assertEquals("Test  with node that doesn't have path", -1, FW.timeRequiredforCompetition());

         FW = new CompetitionFloydWarshall("input-J.txt", 98, 70, 84);
        assertEquals("Smile", -1, FW.timeRequiredforCompetition());

         FW = new CompetitionFloydWarshall("tinyEWD.txt", 5, 80, 60);
        assertEquals("Test competition with less than 50 speed", -1, FW.timeRequiredforCompetition());
    }

    public void testTimeRequiredforCompetitionrD() throws IOException {
		CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 49, 50, 101);
		assertEquals(-1, dijkstra.timeRequiredforCompetition()); 

		dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 50, 50);
		assertEquals(38, dijkstra.timeRequiredforCompetition()); 

}
}