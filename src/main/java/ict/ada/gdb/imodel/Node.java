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

    boolean isMaster();

    int data();

    List<Integer> mirrors();

    Collection<Node> neighbors();

    Collection<Node> neighbors(String tailType);

    Collection<Node> neighbors(String tailType, String relType);

}
