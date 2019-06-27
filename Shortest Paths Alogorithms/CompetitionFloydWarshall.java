
import java.io.BufferedReader;

import java.io.FileReader;
/**
 * 
 * @author pierceam
 *
 */
/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */


public class CompetitionFloydWarshall {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */

    private static final double INFINITY = Integer.MAX_VALUE / 2;   // to prevent overflow if you do INFINITY + INFINITY

    double table[][];    

    int sA, sB, sC;
    int numberOfVertex, numberOfEdges;    
    int slowestPath;

    String filename;

    boolean validFile = true;

    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
        this.filename = filename;
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
        this.readFile();
    }

    private void readFile(){
        slowestPath = Math.min(sA, sB);
        slowestPath = Math.min(slowestPath, sC);

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            numberOfVertex = Integer.parseInt(br.readLine());
            numberOfEdges = Integer.parseInt(br.readLine());
            if(numberOfVertex == 0 || numberOfEdges == 0 ){
                validFile = false;
            }
            if(filename == null){
                validFile = false;
                slowestPath = -1;
            }
            else{
                table = new double[numberOfVertex][numberOfEdges];
                for (int indexi = 0; indexi < numberOfVertex; indexi++){
                    for (int indexj = 0; indexj < numberOfVertex; indexj++){
                        table[indexi][indexj] = INFINITY;
                    }
                }
                String line = br.readLine();
                while((line != null)){
                    String[] lSplit = line.trim().split(" ");
                    table[Integer.parseInt(lSplit[0])][Integer.parseInt(lSplit[1])] = Double.parseDouble(lSplit[2]);
                    line = br.readLine();
                }
                br.close();
            }
        }catch (Exception e){
            validFile = false;
            slowestPath = -1;
        }
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
        if((sA > 100 || sA < 50) || (sB > 100 || sB < 50) || (sC > 100 || sC < 50)){
            return -1;
        }

        if(!validFile){
            return -1;
        }
        for (int indexk = 0; indexk < numberOfVertex; indexk++){
            for (int indexi = 0; indexi < numberOfVertex; indexi++){
                for (int indexj = 0; indexj < numberOfVertex; indexj++){
                    if(table[indexi][indexk] + table[indexk][indexj] < table[indexi][indexj]){
                        table[indexi][indexj] = table[indexi][indexk] + table[indexk][indexj];
                    }
                }
            }
        }
        double maxDist = Max();
        if(maxDist == INFINITY){
            return -1;
        }
        maxDist = maxDist * 1000;   

        return (int) Math.ceil(maxDist / slowestPath);
    }

    private double Max(){
        double Dist = -1;
        for (int indexi = 0; indexi < numberOfVertex; indexi++){
            for (int indexj = 0; indexj < numberOfVertex; indexj++){
                if(table[indexi][indexj] > Dist && indexi != indexj){
                    Dist = table[indexi][indexj];
                }
            }
        }
        return Dist;
    }

}