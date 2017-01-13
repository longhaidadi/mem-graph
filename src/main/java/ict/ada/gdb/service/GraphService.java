package ict.ada.gdb.service;

import ict.ada.gdb.imodel.Attribute;
import ict.ada.gdb.imodel.Node;
import ict.ada.gdb.imodel.Relation;
import ict.ada.gdb.model.GDBAttribute;
import ict.ada.gdb.model.GDBNode;
import ict.ada.gdb.model.GDBRelation;
import ict.ada.gdb.model.GraphMeta;
import ict.ada.gdb.proxy.NodeProxy;
import ict.ada.gdb.proxy.RelationProxy;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by lon on 17-1-10.
 */
public class GraphService {

    private Properties properties ;

    private String graphName ;

    private int graphId;

    private static String IDFLAG = "i";

    private static String ISMASTER = "m";

    private static String SLAVES = "s";

    private static String DATA = "d";

    private static String COLOR = "c";

    private static String IN = "i";

    private static String OUT = "o";

    public GraphService(String graphName,Properties properties) {

        this.graphName = graphName;
        this.graphId = getGraphMeta().getId();
        this.properties = properties;
    }

    /**
     * 根据节点类型和节点id,获取节点参与运算的基本信息.
     * **/


    public GraphMeta getGraphMeta(){

        return new MetaDataStorage().getGraphMetadata(graphName);
    }

    public Node getNode(int nType, String nodeId){

        Jedis jedis = TableManager.selectComputeTable(graphId);
        Map<String,String> resultMap = jedis.hgetAll(ID.makeNodeIdKey(nType, nodeId));

        if(resultMap==null || resultMap.isEmpty() )
            return null;

        int data = Integer.parseInt(resultMap.get(DATA));
        String slaves = resultMap.get(SLAVES);
        int color = Integer.parseInt(COLOR);
        boolean isMaster = Boolean.parseBoolean(resultMap.get(ISMASTER));

        GDBNode node = new GDBNode(nType,nodeId);
        node.setIsMaster(isMaster);
        node.setData(data);
        node.setSlaves(slaves);
        node.setColor(color);
        return new NodeProxy(this,node);
    }

    /**
     * 在持久层增加一个节点,包括节点参与运算的值
     * **/

    public Node addNode(int nType, String nodeId, int value){
        Jedis jedis = TableManager.selectComputeTable(graphId);
        jedis.hset(ID.makeNodeIdKey(nType, nodeId), IDFLAG, nType + "_" + nodeId);
        jedis.hset(ID.makeNodeIdKey(nType, nodeId), ISMASTER, "false");
        jedis.hset(ID.makeNodeIdKey(nType, nodeId), SLAVES, "");
        jedis.hset(ID.makeNodeIdKey(nType, nodeId), COLOR, "0");
        jedis.hset(ID.makeNodeIdKey(nType,nodeId),DATA,String.valueOf(value));
        return new NodeProxy(this,new GDBNode(nType,nodeId));
    }


    public boolean existNode(int nType, String nodeId){

        Jedis jedis = TableManager.selectComputeTable(graphId);
        return jedis.hexists(ID.makeNodeIdKey(nType, nodeId),"_id");
    }
    /**
     *如果节点存在,则为节点添加属性;否则返回false
     * **/
    public boolean addNodeAttr(int nType, String nodeId,String key , String value){

        if(value==null ||!existNode(nType,nodeId) )return false;

        Jedis jedis = TableManager.selectAttributeTable(graphId);
        jedis.hset(ID.makeNodeIdKey(nType,nodeId),key,value.toString());

        return true;

    }

    /**
     * 获取指定节点的属性
     * */
    public String getNodeAttr(int nType, String nodeId,String attrKey){

        if(existNode(nType,nodeId)){
            Jedis jedis = TableManager.selectAttributeTable(graphId);
            return jedis.hget(ID.makeNodeIdKey(nType,nodeId),attrKey);

        }
        return null;
    }
    public List<Attribute> getNodeAttrs(int nType, String nodeId){

        List<Attribute> attributes = new ArrayList<Attribute>();
        if(existNode(nType,nodeId)){

            Jedis jedis = TableManager.selectAttributeTable(graphId);
            Map<String,String> tmpMap = jedis.hgetAll(ID.makeNodeIdKey(nType, nodeId));
            for (Map.Entry<String,String> entry : tmpMap.entrySet()){
                Attribute attribute = new GDBAttribute(entry.getKey(),entry.getValue());
                attributes.add(attribute);
            }
            return attributes;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 增加一个关系,并且增加关系中参与运算的基本值
     * **/
    public Relation addRelation(GDBNode src, GDBNode des, int rType, int val){
        Jedis jedis = TableManager.selectComputeTable(graphId);
        jedis.hset(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType),IDFLAG,
                src.getType()+"_"+src.getId()+"_"+des.getType()+"_"+des.getId()+"_"+rType);
        jedis.hset(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType),ISMASTER,"false");
        jedis.hset(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType),SLAVES,"");
        jedis.hset(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType), COLOR, "0");
        jedis.hset(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType), DATA, String.valueOf(val));

        jedis.lpush(ID.makeNodeIdKey(src.getType(), src.getId()), ID.makeNeighborId(des.getType(), des.getId(), rType));
        jedis.lpush(ID.makeNodeIdKey(des.getType(), des.getId()), ID.makeNeighborId(src.getType(), src.getId(), rType));
        return new RelationProxy(new GDBRelation(src,des,rType),this);
    }

    public Relation getRelation(GDBNode src, GDBNode des, int rType ){
        if(!existRelation(src,des,rType)) return null;
        Jedis jedis = TableManager.selectComputeTable(graphId);
        Map<String,String> resultMap = jedis.hgetAll(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType));

        if(resultMap==null || resultMap.isEmpty() )
            return null;
        int data = Integer.parseInt(resultMap.get(DATA));
        String slaves = resultMap.get(SLAVES);
        int color = Integer.parseInt(COLOR);
        boolean isMaster = Boolean.parseBoolean(resultMap.get(ISMASTER));

        GDBRelation relation = new GDBRelation(src,des,rType);
        relation.setIsMaster(isMaster);
        relation.setData(data);
        relation.setSlaves(slaves);
        relation.setColor(color);
        return new RelationProxy(relation,this);


    }

    /**
     * 判断指定首位节点以及关系类型,是否存在关系
     * */
    public boolean existRelation(GDBNode src, GDBNode des, int rType){
        Jedis jedis = TableManager.selectComputeTable(graphId);
        return jedis.hexists(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType),IDFLAG);
    }

    /**
     * 为指定的关系添加属性
     * **/
    public boolean addRelationAttr(GDBNode src, GDBNode des,int rType,String key ,Object value){

        if(value==null ||!existRelation(src, des,rType) )return false;
        Jedis jedis = TableManager.selectAttributeTable(graphId);
        jedis.hset(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType),key,value.toString());

        return true;
    }

    /**
     * 获取指定节点的属性
     * **/
    public String getRelationAttr(GDBNode src, GDBNode des,int rType,String attrKey){

        if(existRelation(src, des,rType)){
            Jedis jedis = TableManager.selectAttributeTable(graphId);
            return jedis.hget(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType),attrKey);

        }
        return null;
    }

    public List<Attribute> getRelationAttrs(GDBNode src, GDBNode des,int rType){

        List<Attribute> attributes = new ArrayList<Attribute>();

        if(existRelation(src, des, rType)){

            Jedis jedis = TableManager.selectAttributeTable(graphId);
            Map<String,String> tmpMap = jedis.hgetAll(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType));
            for (Map.Entry<String,String> entry : tmpMap.entrySet()){
                Attribute attribute = new GDBAttribute(entry.getKey(),entry.getValue());
                attributes.add(attribute);
            }
            return attributes;
        }


        return Collections.EMPTY_LIST;
    }

    /**
     * 获取指定节点的入节点
     * 如果rtype==null,返回所有关系类型的节点
     * **/
    public Collection<Node> neighbors(GDBNode src,int tailType, int rType){

        List<Node> nodes = new ArrayList<Node>();
        if(existNode(src.getType(),src.getId())){
            Jedis jedis = TableManager.selectComputeTable(graphId);
            List<String> neighbors = jedis.lrange(ID.makeNodeIdKey(src.getType(), src.getId()),0,-1);
            for(String neighborId : neighbors){
                String [] array = neighborId.split(ID.ConStr);
                int tT = Integer.parseInt(array[0]);
                int rT = Integer.parseInt(array[1]);
                if(tailType!=-1 && tailType!=tT) continue;
                if(rType!=-1 && rT!= rType) continue;
                nodes.add(new NodeProxy(this,new GDBNode(tailType,array[1])));
            }
            return nodes;
        }
        return Collections.emptyList();
    }


    /*************Update****************/

    public Node updateNodeValue(GDBNode src, int newValue){

        if(existNode(src.getType(), src.getId())) {
            Jedis jedis = TableManager.selectComputeTable(graphId);
            jedis.hset(ID.makeNodeIdKey(src.getType(), src.getId()), DATA, String.valueOf(newValue));
            src.setData(newValue);
            return new NodeProxy(this,src);
        }
        return null;

    }

    public Relation updateRelationValue(GDBNode src, GDBNode des,int rType, int newValue){
        if(existRelation(src,des,rType)){
            Jedis jedis = TableManager.selectComputeTable(graphId);
            jedis.hset(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType), DATA, String.valueOf(newValue));
            GDBRelation relation = new GDBRelation(src,des,rType);
            relation.setData(newValue);
        }
        return null;
    }

    public boolean updateNodeAttribute(int nType, String nodeId, Attribute attr){

        if(attr.value()==null ||!existNode(nType,nodeId) )return false;

        Jedis jedis = TableManager.selectAttributeTable(graphId);
        jedis.hset(ID.makeNodeIdKey(nType,nodeId),attr.key(),attr.value());

        return true;
    }

    public boolean updateRelationAttribute(GDBNode src, GDBNode des,int rType, Attribute attr){

        if(attr.value()==null ||!existRelation(src, des,rType) )return false;
        Jedis jedis = TableManager.selectAttributeTable(graphId);
        jedis.hset(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType),attr.key(),attr.value());

        return true;
    }


    /*****************DEl***********************/
    public boolean delNode(int nType, String nodeId){

        if(existNode(nType,nodeId)){
            Jedis jedis = TableManager.selectAttributeTable(graphId);
            jedis.del(ID.makeNodeIdKey(nType,nodeId));
            jedis = TableManager.selectComputeTable(graphId);
            jedis.del(ID.makeNodeIdKey(nType,nodeId));
            // 还没有删除关系
            return true;
        }

        return false;
    }

    public boolean delNodeAttr(int nType,String nodeId, String attrKey){

        if(existNode(nType,nodeId)){
            Jedis jedis = TableManager.selectAttributeTable(graphId);
            jedis.del(ID.makeNodeIdKey(nType,nodeId));
            return true;
        }

        return false;
    }

    public boolean delRelation(GDBNode src, GDBNode des, int rType){

        if(existRelation(src,des,rType)){
            Jedis jedis = TableManager.selectAttributeTable(graphId);
            jedis.del(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType));
            jedis = TableManager.selectComputeTable(graphId);
            jedis.del(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType));

            return true;
        }
        return false;
    }

    public boolean delRelationAttr(GDBNode src, GDBNode des, int rType, String attrKey){

        if(existRelation(src,des,rType)){
            Jedis jedis = TableManager.selectAttributeTable(graphId);
            jedis.del(ID.makeRelationIdKey(src.getType(), src.getId(), des.getType(), des.getId(), rType));
            return true;
        }
        return false;
    }
}
