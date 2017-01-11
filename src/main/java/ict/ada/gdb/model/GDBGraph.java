package ict.ada.gdb.model;

import java.util.List;

/**
 * The GDB Graph Model
 * <p/>
 * Created by chenbo on 2016/4/1.
 */
public class GDBGraph {
    private GraphMeta metaData;

    private List<GDBGraphElement> elements;

    public GraphMeta getMetaData() {
        return metaData;
    }

    public void setMetaData(GraphMeta metaData) {
        this.metaData = metaData;
    }

    public List<GDBGraphElement> getElements() {
        return elements;
    }

    public void setElements(List<GDBGraphElement> elements) {
        this.elements = elements;
    }
}