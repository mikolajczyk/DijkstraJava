/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

/**
 *
 * @author matthew
 */
import java.util.ArrayList;

//create graph 
public class Graph {

  private Node[] nodes;
  private int noOfNodes;
  private Edge[] edges;
  private int noOfEdges;
  private int start;

  public Graph(Edge[] edges) {
    this.edges = edges;

    // create the nodes
    this.noOfNodes = calculateNumOfNodes(edges);
    this.nodes = new Node[this.noOfNodes];

    for (int n = 0; n < this.noOfNodes; n++) {
      this.nodes[n] = new Node();
    }

    // add edges to nodes
    this.noOfEdges = edges.length;

    for (int edgeToAdd = 0; edgeToAdd < this.noOfEdges; edgeToAdd++) {
      this.nodes[edges[edgeToAdd].getFromNodeIndex()].getEdges().add(edges[edgeToAdd]);
      this.nodes[edges[edgeToAdd].getToNodeIndex()].getEdges().add(edges[edgeToAdd]);
    }

  }

  private int calculateNumOfNodes(Edge[] edges) {
    int numOfNodes = 0;

    for (Edge e : edges) {
      if (e.getToNodeIndex() > numOfNodes)
        numOfNodes = e.getToNodeIndex();
      if (e.getFromNodeIndex() > numOfNodes)
        numOfNodes = e.getFromNodeIndex();
    }

    numOfNodes++;

    return numOfNodes;
  }


  public void calculateShortestDistances(int start) {
    
    this.nodes[start].setDistanceFromSource(0);
    int nextNode = start;
   

    // visit every node
    for (int i = 0; i < this.nodes.length; i++) {
      // loop around the edges of current node
      ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();

      for (int joinedEdge = 0; joinedEdge < currentNodeEdges.size(); joinedEdge++) {
        int neighbourIndex = currentNodeEdges.get(joinedEdge).getNeighbourIndex(nextNode);

        
        if (!this.nodes[neighbourIndex].isVisited()) {
          int tentative = this.nodes[nextNode].getDistanceFromSource() + currentNodeEdges.get(joinedEdge).getLength();

          if (tentative < nodes[neighbourIndex].getDistanceFromSource()) {
            nodes[neighbourIndex].setDistanceFromSource(tentative);
          }
        }
      }

      // all neighbours checked so node visited
      nodes[nextNode].setVisited(true);

      // next node must be with shortest distance
      nextNode = getNodeShortestDistanced(nextNode);

   }
  }

  
  private int getNodeShortestDistanced(int index) {
    int storedNodeIndex = 0;
    int storedDist = Integer.MAX_VALUE;

    for (int i = 0; i < this.nodes.length; i++) {
      int currentDist = this.nodes[i].getDistanceFromSource();

      if (!this.nodes[i].isVisited() && currentDist < storedDist) {
        storedDist = currentDist;
        storedNodeIndex = i;
      }
    }

    return storedNodeIndex;
  }

  // print the result
  public void printResult() {
    String output = "Number of nodes = " + this.noOfNodes;
    output += "\nNumber of edges = " + this.noOfEdges;

    for (int i = 0; i < this.nodes.length; i++) {
      output += ("\nThe shortest distance from node 0 to node " + i + " is " + nodes[i].getDistanceFromSource());
    }

    System.out.println(output);
  }

  public Node[] getNodes() {
    return nodes;
  }

  public int getNoOfNodes() {
    return noOfNodes;
  }

  public Edge[] getEdges() {
    return edges;
  }

  public int getNoOfEdges() {
    return noOfEdges;
  }

}