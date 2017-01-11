package ict.ada.gdb.imodel;

import ict.ada.gdb.model.*;
import ict.ada.gdb.query.QueryMap;
import ict.ada.gdb.query.RelQuerySpec;


import java.util.List;
import java.util.Map;

/**
 * Created by lon on 16-2-19.
 */
public interface Graph extends AutoCloseable {
    /**
     * 获取图的ID
     *
     * @return
     */
    int id();

    /**
     * 获取图的名字
     *
     * @return
     */
    String name();

    /**
     * 获取所有节点
     *
     * @return
     */
    List<Node> listNodes();

    /**
     * 获取所有type类型的节点
     *
     * @param type
     * @return
     */
    List<Node> listNodes(String type);

    /**
     * 根据类型和Id返回节点
     */
    Node getNode(String id, String type);

    /**
     * 根据头节点id,尾节点id返回关系
     **/
    Relation getRelation(String headType, String headId, Direction dir, String tailType, String tailId, String type);

    /**
     * 根据头节点id,尾节点id返回关系
     **/
    Relation getRelation(String headType, String headId, Direction dir, String tailType, String tailId, String type, int tsFrom, int tsTo);

    /**
     * 根据头节点id,尾节点id返回关系
     **/
    List<Relation> getRelations(String headType, String headId, Direction dir, String tailType, String tailId, int tsFrom, int tsTo);


    /**
     *
     */
    List<Relation> getRelations(RelQuerySpec spec);
    /**
     * 根据指定的查询条件，进行关系子图查询
     * @param relQuerySpec
     * @return
     */
    //RelationGraph queryRelation(RelQuerySpec relQuerySpec);

    /**
     * 创建一个新的节点，由参数指定id,name和type
     *
     * @param id
     * @param name
     * @param type
     * @return
     */
    Node newNode(String id, String name, String type);

    /**
     * 创建一个新的节点，由参数指定name和type，系统生生ID
     *
     * @param name
     * @param type
     * @return
     */
    Node newNode(String name, String type);

    /**
     * 删除节点
     *
     * @param nodeId
     */
    void deleteNode(String type, String nodeId);

    /**
     * 删除type类型的所有节点
     */
    void deleteNodes(String type);

    /**
     * 删除指定类型的边
     *
     * @param headType
     * @param headId
     * @param tailType
     * @param tailId
     */
    void deleteRelation(String headType, String headId, String tailType, String tailId, String type);

    /**
     * 删除边
     *
     * @param headType
     * @param headId
     * @param tailType
     * @param tailId
     */
    void deleteRelation(String headType, String headId, String tailType, String tailId);

    /**
     * 清空整个图的数据
     */
    void clear();

    /**
     * 根据头节点,尾节点新建一条边
     *
     * @param head
     * @param tail
     * @param relationType
     * @return
     */
    public Relation newRelation(Node head, Node tail, String relationType);

    /**
     * @param headName
     * @param headType
     * @param tailName
     * @param tailType
     * @param relationType
     * @return
     */
    public Relation newRelation(String headName, String headType, String tailName, String tailType, String relationType);

    /**
     * 返回当前图的meta
     *
     * @return
     */
    public GraphMeta graphMeta();

    /**
     * 保存当前图的meta
     */
    public void saveGraphMeta();


    Map<GDBNode, RelationGraph> getNodesInTwoLevel(RelQuerySpec spec1, final RelQuerySpec spec2);


    /**
     * 查询图中节点个数
     */
    public long getNodeCount() ;

    /***
     *查询图中关系个数
     */

    public long getRelCount() ;


    /**
     * 根据节点的sname,attributes检索节点
     * */

    public List<GDBNode> query(int nodeType, List<QueryMap> queries, int start, int len);

    /**
     * 根据节点的sname,attributes检索节点个数
     * */

    public long queryCount(int nodeType, List<QueryMap> queries);

    /**
     * 返回图中节点各种类型的个数信息
     * */

    /**
     * 返回图中关系各种类型的个数信息
     * */
    Map<String,Long> getNodeTypeCount();

    Map<String,Long> getRelationTypeCount();

    List<GDBPath> shortestPath(String headId, String headType, String tailId, String tailType, int depth, int maxOut, int maxPathCount);

    void delete();
}
