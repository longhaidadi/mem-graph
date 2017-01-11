package ict.ada.gdb.model;

import ict.ada.gdb.imodel.Node;

import java.util.*;
import java.util.Map.Entry;

/**
 * The Graph formed by Paths between 2 nodes. <br>
 * This Graph can carry data on Edge, but can not carry data on Node (so you need to fetch each
 * Node's data separately if you need).
 * <p/>
 * Internally, Graph is stored as adjacency list. <br>
 * It is guaranteed that one conceptual Node has only one Node object.
 */
public class PathGraph {

    private boolean canReached = false;


    private Node graphStart;// shared start node
    private Node graphEnd;// shared end node
    private Map<Node, Set<Node>> adjNodeList;// adjacency nodes list of this graph.

    //private Map<Node, List<Relation>> adjRelationList;

    // IMPORTANT:
    // Make sure one conceptual Node has only one Node object, so that Node's data(e.g. attrs) will
    // not be duplicated and enable us to use Node as Map key and Set content.
    private Map<String, Node> idNodeMap;// Node id ==> Node object

    public PathGraph(Node startNode, Node endNode) {
        this.adjNodeList = new HashMap<Node, Set<Node>>();
        this.idNodeMap = new HashMap<String, Node>();
        this.graphStart = startNode;
        this.graphEnd = endNode;

        // in case of an empty PathGraph, initialize start and end nodes' adjList.
        adjNodeList.put(graphStart, new HashSet<Node>());
        adjNodeList.put(graphEnd, new HashSet<Node>());
    }


    public boolean addNode(Node start, Node end) {
        return internalAddDirectedEdge(start, end);
    }


    private boolean internalAddDirectedEdge(Node start, Node end) {
        Set<Node> adjNodes = adjNodeList.get(start);
        if (adjNodes == null) {
            adjNodes = new HashSet<Node>(); // use Set to avoid duplicate insert
            adjNodes.add(end);
            adjNodeList.put(start, adjNodes);
            return true;
        } else {
            if (adjNodes.contains(end)) {
                return false;// duplicate Edge, so the given Edge is ignored.
            } else {
                adjNodes.add(end);
                return true;
            }
        }
    }


    /**
     * Get all Node in this graph.
     *
     * @return
     */
    public Collection<Node> getNodeList() {
        if (idNodeMap != null) {
            return idNodeMap.values();
        } else {
            return Collections.emptySet();
        }
    }

    /**
     * Get the statistics of this PathGraph.
     *
     * @return
     */
    public PathGraphStatistics getGraphStatistics() {
        PathGraphStatistics stat = new PathGraphStatistics();
        stat.nodeNum = getNodeList().size();

        int maxD = -1, minD = Integer.MAX_VALUE, totalD = 0;
        for (Entry<Node, Set<Node>> entry : adjNodeList.entrySet()) {
            int degree = entry.getValue().size();
            totalD += degree;
            maxD = Math.max(maxD, degree);
            minD = Math.min(minD, degree);
        }
        stat.maxDegree = maxD;
        stat.minDegree = minD;
        stat.edgeNum = totalD / 2;
        stat.avgDegree = totalD * 1.0 / stat.nodeNum;
        return stat;
    }

    public boolean isCanReached() {
        return canReached;
    }

    public void setCanReached(boolean canReached) {
        this.canReached = canReached;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========PathGraph=======\n");
        sb.append("Start Node=" + graphStart + "\n");
        sb.append("End node=" + graphEnd + "\n");
        sb.append("Total Nodes=" + getNodeList().size() + "\n");
        sb.append("========================\n");
        return sb.toString();
    }

    /**
     * start node
     */
    public Node getGraphStart() {
        return graphStart;
    }

    /**
     * end node
     */
    public Node getGraphEnd() {
        return graphEnd;
    }

    /**
     * @return the adjNodeList
     */
    public Map<Node, Set<Node>> getAdjNodeList() {
        return adjNodeList;
    }

    public static class PathGraphStatistics {
        private int nodeNum;
        private int edgeNum;
        private int maxDegree;
        private int minDegree;
        private double avgDegree;

        public int getNodeNum() {
            return nodeNum;
        }

        public int getEdgeNum() {
            return edgeNum;
        }

        public int getMaxDegree() {
            return maxDegree;
        }

        public int getMinDegree() {
            return minDegree;
        }

        public double getAvgDegree() {
            return avgDegree;
        }

        @Override
        public String toString() {
            return "[PathGraph Statistics] NodeNumber=" + nodeNum + "\tEdgeNumber=" + edgeNum
                    + "\tMaxDegree=" + maxDegree + "\tMinDegree=" + minDegree + "\tAvgDegree=" + avgDegree;
        }
    }


}
