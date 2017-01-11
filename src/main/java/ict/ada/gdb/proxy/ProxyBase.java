package ict.ada.gdb.proxy;

import ict.ada.gdb.model.GraphMeta;
import ict.ada.gdb.service.GraphService;

/**
 * Created by lon on 17-1-10.
 */
public class ProxyBase {

    GraphService gs;

    protected ProxyBase(GraphService service) {
        this.gs = service;
    }

    protected String getRelType(int rt){

        return gs.getGraphMeta().getRelType(rt);
    }

    protected int getRelType(String rt){

        return gs.getGraphMeta().getRelType(rt);
    }

    protected String getNodeType(int nt){

        return gs.getGraphMeta().getNodeType(nt);
    }

    protected int getNodeType(String nt){

        return gs.getGraphMeta().getNodeType(nt);
    }

}
