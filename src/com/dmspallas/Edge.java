package com.dmspallas;

public class Edge {
    private int fromNodeIndex;
    private int length;
    int toNodeIndex;

    public Edge(int fromNodeIndex, int length, int toNodeIndex) {
        this.fromNodeIndex = fromNodeIndex;
        this.toNodeIndex = toNodeIndex;
        this.length = length;
    }

    public int getFromNodeIndex() {
        return fromNodeIndex;
    }

    public int getLength() {
        return length;
    }

    public int getToNodeIndex() {
        return toNodeIndex;
    }

    // determines the neighbouring node of supplying node, based on the two node connected by this edge;
    public int getNeighbourIndex(int nodeIndex) {
        if (this.fromNodeIndex == nodeIndex) {
            return this.toNodeIndex;
        } else {
            return this.fromNodeIndex;
        }
    }
}
