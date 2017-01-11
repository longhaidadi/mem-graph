package ict.ada.gdb.model;




/**
 * Created by chenbo on 2016/3/31.
 */
public class GDBNode extends GDBGraphElement {
    private String name;

    private boolean isMaster;
    private String slaves;
    private int data;
    private int color;



    public GDBNode(int type ,String id) {
        super.setId(id == null ? name : id);
        super.setType(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GDBNode)
            return ((GDBNode) obj).getId().equals(this.getId()) && ((GDBNode) obj).getType() == this.getType();
        return false;
    }

    @Override
    public int hashCode() {
        return (this.getType() + this.getId()).hashCode();
    }

    @Override
    public String toString() {
        return "GDBNode{" +
                "name='" + name + '\'' +
                ", isMaster=" + isMaster +
                ", slaves='" + slaves + '\'' +
                ", data=" + data +
                ", color=" + color +
                '}';
    }

    public boolean isMaster() {
        return isMaster;
    }

    public void setIsMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }

    public String getSlaves() {
        return slaves;
    }

    public void setSlaves(String slaves) {
        this.slaves = slaves;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
