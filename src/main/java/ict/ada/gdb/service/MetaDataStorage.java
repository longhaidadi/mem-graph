package ict.ada.gdb.service;

import ict.ada.gdb.imodel.Graph;
import ict.ada.gdb.model.GraphMeta;
import ict.ada.gdb.model.IMetaDataStorage;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by lon on 17-1-10.
 */
public class MetaDataStorage implements IMetaDataStorage {

   // private Map<String ,GraphMeta> graphMateMap = new HashMap<String, GraphMeta>();

    private static String PREFIX = "__";
    static Jedis jedis;
    static {
         jedis = TableManager.selectMetaTable();
    }

    public MetaDataStorage() {
        listGraphs();
    }

    private int graphSize(){
        return graphNames().size();
    }

    private List<String> graphNames(){
        List<String> gns = jedis.lrange(PREFIX, 0, -1);
        return gns;
    }

    private boolean isExistGraph(String graphName){
        List<String> gns = graphNames();
        for(String gn: gns){
            if(graphName.equals(gn))return true;
        }
        return false;
    }

    public List<GraphMeta> listGraphs() {

        List<GraphMeta> metas = new ArrayList<GraphMeta>();
        List<String> gns = jedis.lrange(PREFIX, 0, -1);

        for(String graphName : gns) {

            GraphMeta meta = getGraphMetadata(graphName);
            metas.add(meta);
        }
        return metas;
    }

    public GraphMeta getGraphMetadata(String graphName) {
        Jedis jedis = TableManager.selectMetaTable();
        String gn = PREFIX + graphName;

        int graphId = jedis.hget(gn,"graphId")==null?-1:Integer.parseInt(jedis.hget(gn,"graphId"));

        if(graphId<0) {
            System.out.println("graphName: " + graphName + "does not exist");
            return null;
        }

        GraphMeta meta = new GraphMeta(graphId,this);

        meta.setName(graphName);

        Map<String,String> tmpMap = jedis.hgetAll(gn);

        for(Map.Entry<String,String> entry : tmpMap.entrySet()){

            String key = entry.getKey();

            String nodeTypePrefix = PREFIX + "nType";
            String relTypePrefix = PREFIX + "rType";

            if(key.startsWith(nodeTypePrefix)){
                int id = Integer.parseInt(entry.getValue());
                String nodeType = key.substring(nodeTypePrefix.length());
                meta.addNodeType(nodeType,id);
            }
            else if(key.startsWith(relTypePrefix)){
                int id = Integer.parseInt(entry.getValue());
                String relType = key.substring(relTypePrefix.length());
                meta.addRelType(relType,id);
            }

        }
        return meta;
    }

    public int createGraph(String graphName) {
        if(isExistGraph(graphName))return getGraphMetadata(graphName).getId();
        int graphId ;
        String gn = PREFIX + graphName;

        graphId = jedis.incr(PREFIX+"graphid").intValue()-1;
        jedis.lpush(PREFIX, graphName);
        jedis.hset(gn,"graphId",String.valueOf(graphId));

        return graphId;
    }


    public int getNodeType(String graphName, String nodeType) {

        if(!isExistGraph(graphName))
            createGraph(graphName);

        String gn = PREFIX + graphName;

        if(jedis.hexists(gn, PREFIX + "nType" + nodeType))
            return Integer.parseInt(jedis.hget(gn, PREFIX + "nType" + nodeType));
        else{
            int nTypeSize = jedis.hincrBy(gn,"nTypeSize",1L).intValue()-1;
            jedis.hset(gn, PREFIX + "nType" + nodeType, String.valueOf(nTypeSize));
            return nTypeSize;
        }


    }

    public int getRelType(String graphName, String relType) {

        if(!isExistGraph(graphName))
            createGraph(graphName);
        String gn = PREFIX + graphName;

        if(jedis.hexists(gn, PREFIX + "rType" + relType))
            return Integer.parseInt(jedis.hget(gn, PREFIX + "rType" + relType));
        else {
            int nRelSize = jedis.hincrBy(gn,"rTypeSize",1L).intValue()-1;
            jedis.hset(gn, PREFIX + "rType" + relType, String.valueOf(nRelSize));
            return nRelSize;
        }
    }

    public boolean deleteGraph(String graphName) {
        String gn = PREFIX + graphName;
        if(!jedis.exists(graphName) || !jedis.exists(gn)) return false;
        jedis.lpop(graphName);
        jedis.del(gn);
        return true;
    }

    public String getGraphNameById(int id) {
        List<String> gns = graphNames();
        for(String graphName : gns){
            GraphMeta meta  = getGraphMetadata(graphName);
            if(meta.getId() == id)
                return graphName;
        }
        return null;
    }


    public void clear() {
        jedis.flushDB();
    }
}
