package ict.ada.gdb.proxy;

import ict.ada.gdb.imodel.Attribute;
import ict.ada.gdb.imodel.Node;
import ict.ada.gdb.imodel.Occurance;
import ict.ada.gdb.model.GDBAttribute;
import ict.ada.gdb.model.GDBNode;
import ict.ada.gdb.service.GraphService;
import ict.ada.gdb.service.ID;

import java.util.Collection;
import java.util.List;

/**
 * Created by lon on 17-1-10.
 */
public class NodeProxy extends ProxyBase implements Node {
    private GDBNode node ;

    public NodeProxy(GraphService gs, GDBNode node){

        super(gs);
        this.node = node;
    }
    public String id() {

        return node.getId();
    }

    public String name() {

        Object name =  gs.getNodeAttr(node.getType(),node.getId(), ID.Name);

        return name==null?null:(String)name;
    }

    public String type() {

        return getNodeType(node.getType());
    }


    public Collection<Node> getInNodes() {

        return gs.inNodes(node.getType(), node.getId(), -1, -1);
    }

    public Collection<Node> getInNodes(String tailType) {

        return gs.inNodes(node.getType(), node.getId(), getNodeType(tailType), -1);
    }

    public Collection<Node> getInNodes(String tailType, String relType) {

        return gs.inNodes(node.getType(), node.getId(), getNodeType(tailType), getRelType(relType));
    }

    public Collection<Node> getOutNodes() {

        return gs.outNodes(node.getType(), node.getId(), -1, -1);
    }

    public Collection<Node> getOutNodes(String tailType) {

        return gs.outNodes(node.getType(), node.getId(), getNodeType(tailType), -1);
    }

    public Collection<Node> getOutNodes(String tailType, String relType) {

        return gs.outNodes(node.getType(), node.getId(), getNodeType(tailType), getRelType(relType));
    }

    public int compareTo(Node o) {

        return (this.type() + this.id()).compareTo(o.type() + o.id());
    }

    public List<Attribute> attributes() {

        return gs.getNodeAttrs(node.getType(), node.getId());
    }

    public boolean addAttribute(String key, Object value) {

        node.addAttribute(new GDBAttribute(key,value));

        return gs.addNodeAttr(node.getType(), node.getId(), key, value);
    }

    public Object getAttributeValue(String key) {

        return gs.getNodeAttr(node.getType(),node.getId(),key);
    }


    public boolean deleteAllAttributes() {

        List<Attribute> attributes = attributes();

        boolean isOk = true;

        for(Attribute attr : attributes){
            isOk = isOk&deleteAttributes(attr.key());
            if(!isOk)
                break;
            node.delAttribute(attr.key());
        }

        return isOk && node.getAttributes().isEmpty();
    }

    public boolean deleteAttributes(String key) {

        node.delAttribute(key);
        return gs.delNodeAttr(node.getType(),node.getId(),key);
    }

    public boolean isMaster() {
        return false;
    }

    public int data() {
        return 0;
    }

    public List<Integer> mirrors() {
        return null;
    }
}
