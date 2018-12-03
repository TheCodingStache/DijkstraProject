package com.dmspallas;

import java.util.ArrayList;

//creating graph object to implement dijsktra algorithm
public class Graph {
    private Node[] nodes;
    private int numberOfEdges;
    private Edge[] edges;
    private int numberOfNodes;

    public Graph(Edge[] edges) {
        this.edges = edges;
        //create all nodes ready to be updated with the edges
        this.numberOfNodes = calculateNoOfNodes(edges);
        this.nodes = new Node[this.numberOfNodes];

        for (int n = 0; n < this.numberOfNodes; n++) {
            this.nodes[n] = new Node();
        }

        this.numberOfEdges = edges.length;

        for (int edgeToAdd = 0; edgeToAdd < this.numberOfEdges; edgeToAdd++) {
            this.nodes[edges[edgeToAdd].getFromNodeIndex()].getEdges().add(edges[edgeToAdd]);
            this.nodes[edges[edgeToAdd].getToNodeIndex()].getEdges().add(edges[edgeToAdd]);
        }

    }

    private int calculateNoOfNodes(Edge[] edges) {
        int numberOfNodes = 0;

        for (Edge e : edges) {
            if (e.getToNodeIndex() > numberOfNodes)
                numberOfNodes = e.getToNodeIndex();
            if (e.getFromNodeIndex() > numberOfNodes)
                numberOfNodes = e.getFromNodeIndex();
        }
        numberOfNodes++;
        return numberOfNodes;
    }

    public void calculateShortestDistance() {
        this.nodes[0].setDistanceFromSource(0);
        int nextNode = 0;

        // visit every node
        for (int i = 0; i <= this.nodes.length; i++) {
            //loop around the edges of current node
            ArrayList<Edge> currentNodeEdge  = this.nodes[nextNode].getEdges();
            for (int joinedEdge = 0; joinedEdge < currentNodeEdge.size(); joinedEdge++) {
                int neighbourIndex = currentNodeEdge.get(joinedEdge).getNeighbourIndex(nextNode);

                //only if now visited
                if (this.nodes[neighbourIndex].isVisited()) {
                    int tentative = this.nodes[nextNode].getDistanceFromSource() + currentNodeEdge.get(joinedEdge).getLength();
                    if (tentative < nodes[neighbourIndex].getDistanceFromSource()) {
                        nodes[neighbourIndex].setDistanceFromSource(tentative);

                    }
                }
            }
            //all neighbours checked so visited
            nodes[nextNode].setVisited(true);
            //next node must be with shortest distance
            nextNode = getNodeShortestDistance();
        }
    }

    private int getNodeShortestDistance() {
        int storedNodeIndex = 0;
        int storedDist = Integer.MAX_VALUE;

        for (int i = 0; i < this.nodes.length; i++) {
            int currentDistance = this.nodes[i].getDistanceFromSource();

            if (!this.nodes[i].isVisited() && currentDistance < storedDist) {
                storedDist = currentDistance;
                storedNodeIndex = i;
            }
        }
        return storedNodeIndex;
    }

    public void displayResult() {
        String output = "Number of nodes = " + this.numberOfNodes;
        output += "\nNumber of edges = " + this.numberOfEdges;

        for (int i = 0; i < this.nodes.length; i++) {
            output += ("\n The shortest distance from node 0 to node " + i + " is " + nodes[i].getDistanceFromSource());
        }
        System.out.println(output);
    }

    public Node[] getNodes() {
        return nodes;
    }

    public int getNoOfEdges() {
        return numberOfEdges;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public int getNoOfNodes() {
        return numberOfNodes;
    }
}
