package ict.ada.gdb.proxy;

import ict.ada.gdb.imodel.Graph;
import ict.ada.gdb.imodel.Node;
import ict.ada.gdb.imodel.Relation;
import ict.ada.gdb.model.*;
import ict.ada.gdb.query.QueryMap;
import ict.ada.gdb.query.RelQuerySpec;
import ict.ada.gdb.service.GraphService;
import ict.ada.gdb.service.MetaDataStorage;

import java.util.List;
import java.util.Map;

/**
 * Created by lon on 17-1-10.
 */
public class GraphProxy extends ProxyBase implements Graph {

    private GraphMeta meta ;
    public GraphProxy(GraphService service, String graphName){
        super(service);
        IMetaDataStorage storage = new MetaDataStorage();
        meta = storage.getGraphMetadata(graphName);
    }
    public int id() {
        return meta.getId();
    }

    public String name() {
        return meta.getName();
    }

    public List<Node> listNodes() {
        return gs.listNodes(-1);
    }

    public List<Node> listNodes(String type) {
        return null;
    }

    public Node getNode(String id, String type) {
        return null;
    }

    public Relation getRelation(String headType, String headId, Direction dir, String tailType, String tailId, String type) {
        return null;
    }

    public Relation getRelation(String headType, String headId, Direction dir, String tailType, String tailId, String type, int tsFrom, int tsTo) {
        return null;
    }

    public List<Relation> getRelations(String headType, String headId, Direction dir, String tailType, String tailId, int tsFrom, int tsTo) {
        return null;
    }

    public List<Relation> getRelations(RelQuerySpec spec) {
        return null;
    }

    public Node newNode(String id, String name, String type) {
        return null;
    }

    public Node newNode(String name, String type) {
        return null;
    }

    public void deleteNode(String type, String nodeId) {

    }

    public void deleteNodes(String type) {

    }

    public void deleteRelation(String headType, String headId, String tailType, String tailId, String type) {

    }

    public void deleteRelation(String headType, String headId, String tailType, String tailId) {

    }

    public void clear() {

    }

    public Relation newRelation(Node head, Node tail, String relationType) {
        return null;
    }

    public Relation newRelation(String headName, String headType, String tailName, String tailType, String relationType) {
        return null;
    }

    public GraphMeta graphMeta() {
        return meta;
    }

    public void saveGraphMeta() {

    }

    public Map<GDBNode, RelationGraph> getNodesInTwoLevel(RelQuerySpec spec1, RelQuerySpec spec2) {
        return null;
    }

    public long getNodeCount() {
        return 0;
    }

    public long getRelCount() {
        return 0;
    }

    public List<GDBNode> query(int nodeType, List<QueryMap> queries, int start, int len) {
        return null;
    }

    public long queryCount(int nodeType, List<QueryMap> queries) {
        return 0;
    }

    public Map<String, Long> getNodeTypeCount() {
        return null;
    }

    public Map<String, Long> getRelationTypeCount() {
        return null;
    }

    public List<GDBPath> shortestPath(String headId, String headType, String tailId, String tailType, int depth, int maxOut, int maxPathCount) {
        return null;
    }

    public void delete() {

    }

    public void close() throws Exception {

    }
}
