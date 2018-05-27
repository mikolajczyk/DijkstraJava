/*this program gets the starting node from the user, then prints out the 
distances from that node to all of the other nodes. 
the edges between the nodes are set to random lengths between 1 and 5.

*/
package dijkstra;

import java.util.Random;
import java.util.Scanner;

public class Djikstra {

  public static void main(String[] args) {
    Random rand = new Random();
    Scanner scan = new Scanner(System.in);
    int start;
    //create array of edges with a random length
    Edge[] edges = {
      new Edge(0, 2, rand.nextInt(5) + 1), new Edge(0, 3, rand.nextInt(5) + 1),new Edge(0, 1, rand.nextInt(5) + 1), 
      new Edge(1, 2, rand.nextInt(5) + 1), new Edge(1, 3, rand.nextInt(5) + 1),new Edge(1, 4, rand.nextInt(5) + 1), 
      new Edge(2, 5, rand.nextInt(5) + 1),new Edge(3, 4, rand.nextInt(5) + 1)
     
    };
    
    System.out.println("which node would you like to start from?");
    start = scan.nextInt();
    Graph g = new Graph(edges);
    g.calculateShortestDistances(start);
    g.printResult(); 
  }

}