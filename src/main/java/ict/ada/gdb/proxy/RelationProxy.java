package ict.ada.gdb.proxy;

import ict.ada.gdb.imodel.Attribute;
import ict.ada.gdb.imodel.Node;
import ict.ada.gdb.imodel.Occurance;
import ict.ada.gdb.imodel.Relation;
import ict.ada.gdb.model.GDBAttribute;
import ict.ada.gdb.model.GDBRelation;
import ict.ada.gdb.service.GraphService;

import java.util.List;

/**
 * Created by lon on 17-1-10.
 */
public class RelationProxy  extends ProxyBase implements Relation {

    private GDBRelation relation;

    public RelationProxy(GDBRelation relation , GraphService service){

        super(service);

        this.relation = relation;
    }

    public Node head() {

        return new NodeProxy(gs,relation.getHead());
    }

    public Node tail() {

        return new NodeProxy(gs,relation.getTail());
    }

    public String headId() {

        return relation.getHeadId();
    }

    public String tailId() {
        return null;
    }

    public String type() {

        return relation.getTailId();
    }

    public String headType() {

        return getRelType(relation.getHeadType());
    }

    public String tailType() {

        return getRelType(relation.getTailType());
    }

    public int weight() {

        return relation.getWeight();
    }

    public List<Attribute> attributes() {

        return gs.getRelationAttrs(relation.getHead(), relation.getTail(), relation.getType());
    }

    public boolean addAttribute(String key, String value) {

        relation.addAttribute(new GDBAttribute(key,value));

        return gs.addRelationAttr(relation.getHead(),relation.getTail(),relation.getType(),key,value);
    }

    public String getAttributeValue(String key) {

        return gs.getRelationAttr(relation.getHead(),relation.getTail(),relation.getType(), key);
    }


    public boolean deleteAllAttributes() {

        List<Attribute> attributes = attributes();

        boolean isOk = true;

        for(Attribute attr : attributes){
            isOk = isOk&deleteAttributes(attr.key());
            if(!isOk)
                break;
        }
        relation.delAllAttributes();
        return isOk;
    }

    public boolean deleteAttributes(String key) {

        relation.delAttribute(key);

        return gs.delRelationAttr(relation.getHead(), relation.getTail(), relation.getType(), key);
    }

}
