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

    protected String id;

    protected int type = -1;//Invalid Type

    protected boolean isMaster;

    protected String slaves;

    protected int data;

    protected int color;

    protected List<Attribute> attributes = new LinkedList<Attribute>();

    protected List<Occurance> occurs = new LinkedList<Occurance>();

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
