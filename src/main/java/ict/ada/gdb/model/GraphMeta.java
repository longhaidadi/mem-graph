package ict.ada.gdb.model;

import ict.ada.gdb.util.DoubleMap;

import java.util.Collection;

/**
 * Created by chenbo on 2016/3/28.
 */
public class GraphMeta  {
    private IMetaDataStorage metaStorage;

    private int id;

    private String name;

    private DoubleMap<String, Integer> nodeTypeMap = new DoubleMap<String, Integer>(16);

    private DoubleMap<String, Integer> relTypeMap = new DoubleMap<String, Integer>(16);


    public GraphMeta(int id, IMetaDataStorage storage) {
        this.id = id;
        this.metaStorage = storage;
    }


    public int getId() {
        return id;
    }

    /*public void setId(int id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNodeTypeCount() {
        return nodeTypeMap.size();
    }

    public int getRelTypeCount() {
        return relTypeMap.size();
    }

    public void addNodeType(String sType, int nType) {
        nodeTypeMap.put(sType, nType);
    }

    public void addRelType(String sType, int nType) {
        relTypeMap.put(sType, nType);
    }


    public boolean hasNodeType(String sType) {
        return nodeTypeMap.hasKey(sType);
    }

    public boolean hasRelType(String sType) {
        return relTypeMap.hasKey(sType);
    }

    /**
     * return existed node type id or new type id
     *
     * @return
     */
    public int getNodeType(String sType) {
        if (nodeTypeMap.hasKey(sType)) {
            return nodeTypeMap.mapKey(sType);
        }
        int t = metaStorage.getNodeType(name, sType);
        nodeTypeMap.put(sType, t);
        return t;
    }

    public String getNodeType(int nType) {
        return nodeTypeMap.mapValue(nType);
    }

    /**
     * return existed rel type id or new type id
     *
     * @param sType
     * @return
     */
    public int getRelType(String sType) {
        if (relTypeMap.hasKey(sType)) {
            return relTypeMap.mapKey(sType);
        }
        int t = metaStorage.getRelType(name, sType);
        relTypeMap.put(sType, t);
        return t;
    }

    public String getRelType(int nType) {
        return relTypeMap.mapValue(nType);
    }


    public Collection<String> getAllNodeTypes() {
        return nodeTypeMap.getFirstMap().keySet();
    }

    public Collection<String> getAllRelationTypes() {
        return relTypeMap.getFirstMap().keySet();
    }



    public void delete(){
        metaStorage.deleteGraph(this);
    }

    @Override
    public String toString() {
        return "GraphMeta{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nodeTypeMap=" + nodeTypeMap +
                ", relTypeMap=" + relTypeMap +
                '}';
    }
}
