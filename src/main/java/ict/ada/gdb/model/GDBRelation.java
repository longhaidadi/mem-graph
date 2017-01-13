package ict.ada.gdb.model;

/**
 * Created by chenbo on 2016/3/31.
 */
public class GDBRelation extends GDBGraphElement {
    private String headId;
    private int headType;
    private String tailId;
    private int tailType;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public GDBRelation(GDBNode head, GDBNode tail, int type) {
        this(head.getType(), head.getId(), tail.getType(), tail.getId(), type);
    }

    public GDBRelation(int headType, String headId, int tailType, String tailId) {
        this(headType, headId, tailType, tailId, ANY_TYPE);
    }

    public GDBRelation(int headType, String headId, int tailType, String tailId, int type) {
        super.setId(headId+tailId);
        super.setType(type);
        this.headId = headId;
        this.headType = headType;
        this.tailId = tailId;
        this.tailType = tailType;
    }

    public GDBNode getHead() {
        return new GDBNode(headType, headId);
    }

    public GDBNode getTail() {
        return new GDBNode(tailType, tailId);
    }

    public String getHeadId() {
        return headId;
    }

    public void setHeadId(String headId) {
        this.headId = headId;
    }

    public int getHeadType() {
        return headType;
    }

    public void setHeadType(int headType) {
        this.headType = headType;
    }

    public String getTailId() {
        return tailId;
    }

    public void setTailId(String tailId) {
        this.tailId = tailId;
    }

    public int getTailType() {
        return tailType;
    }

    public void setTailType(int tailType) {
        this.tailType = tailType;
    }
}
