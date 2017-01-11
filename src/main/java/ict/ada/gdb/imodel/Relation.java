package ict.ada.gdb.imodel;

/**
 * Created by lon on 16-2-19.
 */
public interface Relation extends WithAttribute {
    Node head();

    Node tail();

    String headId();

    String tailId();

    String type();

    String headType();

    String tailType();

    int weight();
}
