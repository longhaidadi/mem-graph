package ict.ada.gdb.model;


import ict.ada.gdb.imodel.Attribute;
import ict.ada.gdb.imodel.Occurance;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenbo on 2016/4/1.
 */
public class GDBGraphElement {
    public static final int ANY_TYPE = 0;

    private String id;

    private int type = -1;//Invalid Type

    private List<Attribute> attributes = new LinkedList<Attribute>();

    private List<Occurance> occurs = new LinkedList<Occurance>();

    public String getIdInGraph() {
        return type + "_" + id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void addAttribute(Attribute attr) {
        attributes.add(attr);
    }

    public void delAttribute(String key,Object v){
        Iterator<Attribute> it = attributes.iterator();
        while (it.hasNext()){
            Attribute attribute = it.next();
            if(key.equals(attribute.key()) && (v==null ||(v.equals(attribute.value())))){
                it.remove();
            }
        }

    }

    public void delAttribute(String key){
        Iterator<Attribute> it = attributes.iterator();
        while (it.hasNext()){
            Attribute attribute = it.next();
            if(key.equals(attribute.key())){
                it.remove();
            }
        }

    }
    public void delAllAttributes(){
        attributes.clear();
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void addOccurance(Occurance occur) {
        occurs.add(occur);
    }

    public List<Occurance> getOccurs() {
        return occurs;
    }

    public void setOccurs(List<Occurance> occurs) {
        this.occurs = occurs;
    }

    @Override
    public String toString() {
        return "GDBGraphElement{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", attributes=" + attributes +
                ", occurs=" + occurs +
                '}';
    }
}
