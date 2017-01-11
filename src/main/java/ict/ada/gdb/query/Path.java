package ict.ada.gdb.query;

import ict.ada.gdb.model.GDBNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 一条“路径”，包含一个或多个有顺序的Node，任意相邻的两点之间有一条Edge相连。
 */
public class Path {
    private LinkedList<GDBNode> orderedNodes;// 路径中包含的有序的一组点。例如：a,b,c,d

    //private List<Relation> orderedRelations;// 路径中点之间的边，顺序依照点的顺序。例如：a-b,b-c,c-d
    //private LinkedList<Node> orderedNodes;
    public Path() {
        orderedNodes = new LinkedList<GDBNode>();
        //orderedRelations = new ArrayList<Relation>();
    }

    public Path(Path path) {
        orderedNodes = new LinkedList<GDBNode>(path.getNodeListInOrder());
        //orderedRelations = new ArrayList<Relation>(path.getRelationListInOrder());
    }

    /**
     * Append one Edge to this GDBPath. The Edge must start with the GDBPath's current tail.
     */
 /* public void appendRelation(Relation relation) {
    if (relation == null)
      throw new NullPointerException("null Edge");
    else if (!getEndNode().equals(relation.head()))
      throw new IllegalArgumentException("The Edge to append does not start with GDBPath's current tail.");

    if (orderedNodes.size() == 0) {
      orderedNodes.add(relation.head());
    }
    orderedNodes.add(relation.tail());
    //orderedRelations.add(relation);
  }*/
    public void appendNode(GDBNode node) {
        if (node == null)
            throw new NullPointerException("null node");
        orderedNodes.add(node);
    }

    public void removeLastNode() {
        if (orderedNodes.isEmpty())
            return;
        orderedNodes.removeLast();
    }

    /**
     * 返回路径的长度，即路径中边的数量。 如果路径仅含单个点或无点，长度为0。
     */
    public long getPathLength() {
        return getNodeListInOrder().size();
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{PATH: length=" + getPathLength() + " ,nodes(size=" + orderedNodes.size() + "):"
                + Arrays.toString(orderedNodes.toArray()) + "}");
        return sb.toString();
    }

    @Override
    public String toString() {
        String res = "{PATH: " + "relations count=" + orderedNodes.size() + " ,nodes(size=" + orderedNodes.size()
                + "):" + Arrays.toString(orderedNodes.toArray()) + "}";
        return res;
    }

    /**
     * 获得路径的起始点
     */
    public GDBNode getStartNode() {
        if (orderedNodes.size() > 0)
            return orderedNodes.get(0);
        else
            return null;
    }

    /**
     * 获得路径的结束点
     */
    public GDBNode getEndNode() {
        if (orderedNodes.size() > 0)
            return orderedNodes.getLast();
        else
            return null;
    }


    /**
     * 返回一个路径中点的列表，列表中依次包含从起点到终点的各个点。
     */
    public List<GDBNode> getNodeListInOrder() {
        return orderedNodes;
    }

    /**
     * 返回一个路径中边的列表，列表中依次包含从起点到终点的各个边。
     */
 /* public List<Relation> getRelationListInOrder() {
    return orderedRelations;
  }
  */

}
