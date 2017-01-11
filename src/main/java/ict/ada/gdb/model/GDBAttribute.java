package ict.ada.gdb.model;

import ict.ada.gdb.imodel.Attribute;
import ict.ada.gdb.util.TimeUtil;

import java.util.List;

/**
 * Created by chenbo on 2016/3/31.
 */
public class GDBAttribute implements Attribute {
    private String key;

    private int ts;

    private Object value;
    private List valueList;

    private List<Integer>tss;

    public GDBAttribute() {

    }

    public GDBAttribute(String key, Object value) {
        this(key, value, TimeUtil.unixTimestamp());
    }

    public GDBAttribute(String key, Object value, int ts) {
        this.key = key;
        this.value = value;
        this.ts = ts;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String key() {
        return key;
    }

    public Object value() {
        return value;
    }

    public int timestamp() {
        return ts;
    }

    public List asList() {
        return valueList;
    }

    public void setValueList(List valueList) {
        this.valueList = valueList;
        if (!valueList.isEmpty())
            this.value = valueList.get(0);
    }

    @Override
    public String toString() {
        return "GDBAttribute{" +
                "key='" + key + '\'' +
                ", ts=" + ts +
                ", value=" + value +
                ", valueList=" + valueList +
                ", tss=" + tss +
                '}';
    }

    public List valueList() {
        return valueList;
    }

    public void setTss(List<Integer> tss) {
        if(!tss.isEmpty())
            this.ts=tss.get(0);
        this.tss = tss;
    }

    public List<Integer> timeList() {
        return tss;
    }
}
