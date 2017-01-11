package ict.ada.gdb.model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by lon on 16-3-16.
 */
public class RelationGraph {
    private final GDBNode centerNode;// center Node
    private final Collection<GDBNode> outerNodes;// outer Nodes that has relation with center Node
    private final Collection<GDBRelation> centerRelations;// Edges from center Node to outer Nodes

    public RelationGraph(GDBNode centerNode) {
        if (centerNode == null) throw new IllegalArgumentException("null centerNode");
        this.centerNode = centerNode;
        outerNodes = new LinkedList<GDBNode>();
        centerRelations = new LinkedList<GDBRelation>();
        // 查询处理时，LinkedList的海量remove性能超过了HashSet，因为remove的顺序与链表中元素顺序相同，使得LinkedList的remove接近O(1)
    }

    public void addCenterRelation(GDBRelation relation) {
        if (relation == null) throw new NullPointerException("null relation");
        if (!relation.getHead().equals(centerNode))
            throw new IllegalArgumentException(
                    "The Edge to add doesn't start with RelationGraph's center Node.");
        centerRelations.add(relation);
        outerNodes.add(relation.getTail());
    }

    /**
     * Add all Edge in edges
     *
     * @param relations
     */
    public void addCenterRelation(Collection<GDBRelation> relations) {
        if (relations != null) {
            for (GDBRelation relation : relations) {
                addCenterRelation(relation);
            }
        }
    }

    public Collection<GDBNode> getOuterNodes() {
        return outerNodes;
    }

    /**
     * 移除一条中心边，同时移除该中心边所连接的相关点
     *
     * @param relation
     */
    public void removeCenterRelatedAndOuterNode(GDBRelation relation) {
        if (relation == null) throw new NullPointerException("null relation");
        if (centerRelations.remove(relation)) {
            outerNodes.remove(relation.getTail());
        }
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=====RelationGraph=====\n");
        sb.append("Center Node: " + centerNode.toString() + "\n");
        sb.append("Center Edges: size=" + centerRelations.size() + "\n");
        for (GDBRelation edge : centerRelations) {
            sb.append("\t" + edge.toString() + "\n");
        }
        sb.append("=======================\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=====RelationGraph=====\n");
        sb.append("Center Node: " + centerNode.toString() + "\n");
        sb.append("Center Edges: size=" + centerRelations.size() + "\n");
        sb.append("=======================\n");
        return sb.toString();
    }

    public Collection<GDBRelation> getCenterRelations() {
        return centerRelations;
    }
}
