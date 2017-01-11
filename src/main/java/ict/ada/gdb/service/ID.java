package ict.ada.gdb.service;

/**
 * Created by lon on 17-1-10.
 */
public class ID {

    public static String ConStr = "-";

    public static String Attr = "a";

    public static String Name = "n";

    public static String makeNodeIdKey(String nType, String nodeId){

        return nType+ConStr+nodeId;
    }

    public static String makeNodeAttrKey(String nType, String nodeId){

        return nType+ConStr+nodeId+ConStr+Attr;
    }

    public static String makeRelationIdKey(String snType,String snId,String tnType,String tnId,String rType){

        return snType+ConStr+snId+ConStr+tnType+ConStr+rType;
    }

    public static String makeRelationAttrKey(String snType,String snId,String tnType,String tnId,String rType){

        return snType+ConStr+snId+ConStr+tnType+ConStr+rType+ConStr+Attr;
    }
}
