package ict.ada.gdb.model;




/**
 * Created by chenbo on 2016/3/31.
 */
public class GDBNode extends GDBGraphElement {
    private String name;
    private Object value;

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
                ", value=" + value +
                '}';
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
