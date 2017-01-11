package ict.ada.gdb.model;

import ict.ada.gdb.imodel.WithTimestamp;

import java.util.List;

/**
 * Created by lon on 16-2-24.
 */
public class GDBAttributeValue implements WithTimestamp {

    private String value;

    private List<GDBOccurance> gdbOccurances;

    private int ts;

    public GDBAttributeValue(String value, List<GDBOccurance> sourceRefs) {
        this.value = value;
        this.gdbOccurances = sourceRefs;
    }

    public int timestamp() {
        return ts;
    }


    public void setTs(int ts) {
        this.ts = ts;
    }

    public String getValue() {
        return value;
    }

    public List<GDBOccurance> getGdbOccurances() {
        return gdbOccurances;
    }

    public void setGdbOccurances(List<GDBOccurance> gdbOccurances) {
        this.gdbOccurances = gdbOccurances;
    }

    @Override
    public String toString() {
        return "GDBAttributeValue{" +
                "value='" + value + '\'' +
                ", sourceRefs=" + gdbOccurances +
                '}';
    }
}
