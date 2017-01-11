package ict.ada.gdb.service;

/**
 * Created by lon on 17-1-10.
 */
public class ID {

    public static String ConStr = "-";

    public static String Attr = "a";

    public static String Name = "n";

    public static String makeNodeIdKey(int nType, String nodeId){

        return nType+ConStr+nodeId;
    }

    public static String makeNodeAttrKey(int nType, String nodeId){

        return nType+ConStr+nodeId+ConStr+Attr;
    }

    public static String makeRelationIdKey(int snType,String snId,int tnType,String tnId,int rType){

        return snType+ConStr+snId+ConStr+tnType+ConStr+rType;
    }

    public static String makeRelationAttrKey(int snType,String snId,int tnType,String tnId,int rType){

        return snType+ConStr+snId+ConStr+tnType+ConStr+rType+ConStr+Attr;
    }
}
