package ict.ada.gdb;

import ict.ada.gdb.imodel.Graph;
import ict.ada.gdb.proxy.GraphProxy;
import ict.ada.gdb.service.GraphService;
import ict.ada.gdb.service.MetaDataStorage;

import java.util.Properties;

/**
 * Created by lon on 17-1-10.
 */
public class GolaxyGraph {

    public  static Graph openGraph(Properties properties, String graphName){


        return new GraphProxy(new GraphService(graphName,properties),graphName);
    }

    public  Graph createGraph(String graphName){

        return null;
    }

    public  boolean delGraph(String graphName){

        return false;
    }

    public  static void clearAllMeta(){
        new MetaDataStorage().clear();
    }
}
