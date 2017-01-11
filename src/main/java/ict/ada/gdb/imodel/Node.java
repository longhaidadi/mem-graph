package ict.ada.gdb.imodel;

import ict.ada.gdb.model.GDBNode;

import java.util.Collection;
import java.util.List;

/**
 * Created by lon on 16-2-19.
 */
public interface Node extends WithAttribute, Comparable<Node> {
    String id();

    String name();

    String type();


    Collection<Node> getInNodes();

    Collection<Node> getInNodes(String tailType);

    Collection<Node> getInNodes(String tailType, String relType);

    Collection<Node> getOutNodes();

    Collection<Node> getOutNodes(String tailType);

    Collection<Node> getOutNodes(String tailType, String relType);


}
