package ict.ada.gdb.model;

import ict.ada.gdb.imodel.SourceRef;

/**
 * Created by chenbo on 2016/3/31.
 */
public class GDBSourceRef implements SourceRef {
    public static GDBSourceRef EMPTY_REF=new GDBSourceRef("0.0");
    private String sourceId;

    private int offset;

    private int length;


    public GDBSourceRef(String sourceId, int offset, int length) {
        this.sourceId = sourceId;
        this.offset = offset;
        this.length = length;
    }

    public GDBSourceRef(String sourceId) {
        this(sourceId, 0, 0);
    }


    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String sourceId() {
        return sourceId;
    }

    public int offset() {
        return offset;
    }

    public int length() {
        return length;
    }

    public String addl() {
        return null;
    }

    @Override
    public String toString() {
        return "GDBSourceRef{" +
                "sourceId='" + sourceId + '\'' +
                ", offset=" + offset +
                ", length=" + length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GDBSourceRef that = (GDBSourceRef) o;

        if (offset != that.offset) return false;
        if (length != that.length) return false;
        return !(sourceId != null ? !sourceId.equals(that.sourceId) : that.sourceId != null);

    }

    @Override
    public int hashCode() {
        int result = sourceId != null ? sourceId.hashCode() : 0;
        result = 31 * result + offset;
        result = 31 * result + length;
        return result;
    }
}
