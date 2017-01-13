package ict.ada.gdb.service;

import redis.clients.jedis.Jedis;

/**
 * Created by lon on 17-1-10.
 */
public class TableManager {
    private static Jedis jedis = RedisInstance.getInstance().getService();

    /**
     * DB allot
     * 0 used all GraphDB Meta
     * graphID*2+1: used store node and relation Attribute
     * graphID*2+2: used store node and node compute value innodesId and outnodesId and relationWeight
     * */

    static Jedis selectAttributeTable(int graphID){
        int nodeDbIndex = graphID *2 +1;
        jedis.select(nodeDbIndex);
        return jedis;
    }

    static Jedis selectMetaTable(){
        int graphMetaIndex = 0;
        jedis.select(graphMetaIndex);
        return jedis;
    }

    static Jedis selectComputeTable(int graphId){
        int relationDbIndex = graphId *2 +2;
        jedis.select(relationDbIndex);
        return jedis;
    }
}
