package ict.ada.gdb.model;

import ict.ada.gdb.imodel.Occurance;
import ict.ada.gdb.imodel.SourceRef;
import ict.ada.gdb.util.TimeUtil;

/**
 * 发生，发生的时间和
 * <p/>
 * Created by chenbo on 2016/3/31.
 */
public class GDBOccurance implements Occurance {
    private int timestamp;//数据产生时间或者入库时间

    private SourceRef ref;//可以为空

    public GDBOccurance() {
        this(TimeUtil.unixTimestamp(), null);
    }

    public GDBOccurance(int ts) {
        this(ts, null);
    }

    public GDBOccurance(SourceRef ref) {
        this(TimeUtil.unixTimestamp(), ref);
    }

    public GDBOccurance(int ts, SourceRef ref) {
        this.timestamp = ts;
        this.ref = ref;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public SourceRef getRef() {
        return ref;
    }

    public void setRef(SourceRef ref) {
        this.ref = ref;
    }

    public int timestamp() {
        return timestamp;
    }

    public SourceRef ref() {
        return ref;
    }

    @Override
    public String toString() {
        return "GDBOccurance{" +
                "timestamp=" + timestamp +
                ", ref=" + ref +
                '}';
    }

    public int compareTo(Occurance o) {
        if(this.getTimestamp()>o.timestamp())
            return 1;
        else if(this.getTimestamp()<o.timestamp())
            return -1;
        if(this.getRef()!=null && o.ref()!=null)
            return this.getRef().sourceId().compareTo(o.ref().sourceId());
        return 0;
    }
}
