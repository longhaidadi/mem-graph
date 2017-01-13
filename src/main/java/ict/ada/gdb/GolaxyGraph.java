package ict.ada.gdb;

import ict.ada.gdb.imodel.Graph;
import ict.ada.gdb.model.GraphMeta;
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

    public  Graph createGraph(String graphName,Properties properties){

        GraphService service = new GraphService(graphName,properties);

        GraphMeta meta = service.getGraphMeta();

        if(meta!=null){

            System.out.println("create graph : " + graphName +" has existed " );
            return new GraphProxy(service,graphName);
        }

        service.createGraphMeta();

        return new GraphProxy(service,graphName);

    }

    public  boolean delGraph(String graphName,Properties properties){

        GraphService service = new GraphService(graphName,properties);

        GraphMeta meta = service.getGraphMeta();

        if(meta==null){

            System.out.println("graph : " + graphName +" does not  exist " );
            return false;
        }
        service.deleteGraphMeta();
        service.delGraph();
        return true;

    }

    public  static void clearAllMeta(){
        new MetaDataStorage().clear();
    }
}
