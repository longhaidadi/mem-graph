package ict.ada.gdb.model;

import java.util.List;

/**
 * 图元数据存储的接口
 * <p/>
 * Created by chenbo on 2016/3/28.
 */
public interface IMetaDataStorage  {
    /**
     * list all graphs
     *
     * @return
     */
    List<GraphMeta> listGraphs();

    /**
     * get graph metadata by its name, or null if not exist
     *
     * @param graphName
     * @return
     */
    GraphMeta getGraphMetadata(String graphName);

    /**
     * create new graph, return new graph ID or 0 if failure
     *
     * @param graphName
     * @return
     */
    int createGraph(String graphName);


    /**
     * return existed node type id or new type id
     *
     * @param graphName
     * @param nodeType
     * @return
     */
    int getNodeType(String graphName, String nodeType);

    /**
     * return existed rel type id or new type id
     *
     * @param graphName
     * @param relType
     * @return
     */
    int getRelType(String graphName, String relType);

    boolean deleteGraph(String graphName);

    String getGraphNameById(int id);

    public void clear();
}
