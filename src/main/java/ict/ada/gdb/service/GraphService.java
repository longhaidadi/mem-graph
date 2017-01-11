package ict.ada.gdb.service;

import ict.ada.gdb.imodel.Attribute;
import ict.ada.gdb.imodel.Node;
import ict.ada.gdb.imodel.Relation;
import ict.ada.gdb.model.GDBAttribute;
import ict.ada.gdb.model.GDBNode;
import ict.ada.gdb.model.GDBRelation;
import ict.ada.gdb.model.GraphMeta;
import redis.clients.jedis.Jedis;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Created by lon on 17-1-10.
 */
public class GraphService {

    private Properties properties ;

    private String graphName ;

    private int graphId;


    public GraphService(String graphName,Properties properties) {

    }

    /**
     * 根据节点类型和节点id,获取节点参与运算的基本信息.
     * **/


    public GraphMeta getGraphMeta(){

        return new MetaDataStorage().getGraphMetadata(graphName);
    }

    public Node getNode(int nType, String nodeId){

        Jedis jedis = TableManager.selectNodeTable(graphId);
        return null;
    }

    /**
     * 在持久层增加一个节点,包括节点参与运算的值
     * **/

    public Node addNode(int ntype, String id, Object value){

        return null;
    }


    public boolean existNode(int nType, String nodeId){

        return false;
    }
    /**
     *如果节点存在,则为节点添加属性;否则返回false
     * **/
    public boolean addNodeAttr(int nType, String nodeId,String key , Object value){

        if(existNode(nType,nodeId)){

        }
        return false;
    }

    /**
     * 获取指定节点的属性
     * */
    public Object getNodeAttr(int nType, String nodeId,String attrKey){

        if(existNode(nType,nodeId)){

        }
        return null;
    }
    public List<Attribute> getNodeAttrs(int nType, String nodeId){

        if(existNode(nType,nodeId)){

        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 增加一个关系,并且增加关系中参与运算的基本值
     * **/
    public Relation addRelation(Node src, Node des, int rType, Object val){

        return null;
    }

    /**
     * 判断指定首位节点以及关系类型,是否存在关系
     * */
    public boolean existRelation(GDBNode src, GDBNode des, int rType){

        return false;
    }

    /**
     * 为指定的关系添加属性
     * **/
    public boolean addRelationAttr(GDBRelation relation,String key ,Object value){

        if(existRelation(relation.getHead(), relation.getTail(), relation.getType())){

        }
        return false;
    }

    /**
     * 获取指定节点的属性
     * **/
    public Object getRelationAttr(GDBRelation relation,String attrKey){

        return null;
    }

    public List<Attribute> getRelationAttrs(GDBNode src, GDBNode des,int rType){

        if(existRelation(src, des, rType)){

        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 获取指定节点的入节点
     * 如果rtype==null,返回所有关系类型的节点
     * **/
    public Collection<Node> inNodes(int nType,String nodeId,int tailType, int rtype){

        return Collections.emptyList();
    }

    /**
     * 获取指定节点的出节点
     * 如果rtype==null,返回所有关系类型的节点
     * **/

    public Collection<Node> outNodes(int nType,String nodeId,int tailType, int rtype){

        return Collections.emptyList();
    }



    /*************Update****************/

    public Node updateNodeValue(int nType, String nodeId, Object newvalue){

        return null;
    }

    public Relation updateRelationValue(int nType, String nodeId, Object newValue){

        return null;
    }

    public boolean updateNodeAttribute(int nType, String nodeId, Attribute attr){

        return false;
    }

    public boolean updateRelationAttribute(int nType, String nodeId, Attribute attr){

        return false;
    }


    /*****************DEl***********************/
    public boolean delNode(int nType, String nodeId){

        if(existNode(nType,nodeId)){
            return false;
        }

        return false;
    }

    public boolean delNodeAttr(int nType,String nodeId, String attrKey){

        return true;
    }

    public boolean delRelation(GDBNode src, GDBNode des, int rtype){

        return true;
    }

    public boolean delRelationAttr(GDBNode src, GDBNode des, int rtype, String attrKey){

        return false;
    }
}
