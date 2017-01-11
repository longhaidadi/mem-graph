package ict.ada.gdb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by chenbo on 2016/4/1.
 */
public class GDBPath {
    private List<GDBNode> path;
    private Map<GDBNode,List<GDBRelation>> relationMaps;
    public GDBPath(){
        path = new ArrayList<GDBNode>();
        relationMaps = new HashMap<GDBNode, List<GDBRelation>>();
    }
    public void setPath(List<GDBNode>nodes){
        this.path=nodes;
    }
    public void addRelation(GDBNode start,List<GDBRelation>relations){
        relationMaps.put(start,relations);
    }

    public List<GDBNode> getPath() {
        return path;
    }

    public Map<GDBNode, List<GDBRelation>> getRelationMaps() {
        return relationMaps;
    }

    @Override
    public String toString() {
        return "GDBPath{" +
                "path=" + path +
                ", relationMaps=" + relationMaps +
                '}';
    }
}
